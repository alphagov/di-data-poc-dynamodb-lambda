package uk.gov.mydata;

import java.util.Date;
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
        // Head off to Dynamo DB and write the data

        for (DataAttribute attribute : request.getAttributes()) {
            // Mark the date as updated now
            attribute.setUpdated(new Date());
        }

        var failed = mapper.batchSave(request.getAttributes());

        if (failed.isEmpty()) {
            var attributes = mapper.batchLoad(request.getAttributes());
            var dataAttributes = attributes.get("attributes").stream().map(a -> (DataAttribute) a)
                    .collect(Collectors.toList());

            Predicate<DataAttribute> notFound = a -> a == null;

            dataAttributes.removeIf(notFound);

            return new DataResponse(dataAttributes);
        } else {
            return new DataResponse();
        }

    }
}