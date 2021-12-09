package uk.gov.mydata;

import java.io.Serializable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBDocument;


@DynamoDBDocument
public class Permission implements Serializable {
    private String service;
    private Boolean read;
    private Boolean write;

    @DynamoDBAttribute(attributeName = "service")
    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    @DynamoDBAttribute(attributeName = "read")
    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    @DynamoDBAttribute(attributeName = "write")
    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public Permission() {
        this.service = "";
        this.read = false;
        this.write = false;
    }

    public Permission(String service, Boolean read, Boolean write) {
        this.service = service;
        this.read = read;
        this.write = write;
    }

}
