package uk.gov.mydata;

import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaRequestHandler
    implements RequestHandler<DataRequest, DataResponse> {

  static AmazonDynamoDB client = AmazonDynamoDBClientBuilder.standard().withRegion(Regions.EU_WEST_1).build();
  static DynamoDB dynamoDB = new DynamoDB(client);
  static DynamoDBMapper mapper = new DynamoDBMapper(client);

  public DataResponse handleRequest(DataRequest request, Context context) {
    // Head off to Dynamo DB and see if we have the attributes we're after

    var attributes = mapper.batchLoad(request.getAttributes());
    var dataAttributes = attributes.get("attributes").stream().map(a -> (DataAttribute) a).collect(Collectors.toList());

    Predicate<DataAttribute> noPermission = a -> !a.getPermissions().stream().anyMatch(p -> {
      return p.getService().equalsIgnoreCase(request.getService())
          && p.getRead().booleanValue() == request.getRead().booleanValue();
    });

    // Now we have our list of attributes, let's check to see if we have permissions
    // to return them
    dataAttributes.stream().filter(noPermission).forEach(a -> {
      a.setPermissionDenied();
    });

    // Also, let's look for any we didn't find
    request.getAttributes().forEach(a -> {
      if (dataAttributes.stream().noneMatch(da -> da.getAttribute().equalsIgnoreCase(a.getAttribute())
          && da.getUser().equalsIgnoreCase(a.getUser()))) {
        a.setNotFound();
        dataAttributes.add(a);
      }
    });

    // Let's hide the permissions
    dataAttributes.forEach(a -> {
      a.setPermissions(null);
    });

    // Now return just the attributes we're allowed to see
    return new DataResponse(dataAttributes);
  }

}