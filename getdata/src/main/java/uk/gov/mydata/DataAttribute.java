package uk.gov.mydata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBRangeKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

@DynamoDBTable(tableName="attributes")
public class DataAttribute implements Serializable {
    private String user;
    private String attribute;
    private String value;
    private Date updated;
    private Boolean notFound;
    private Boolean permissionDenied;
    private List<Permission> permissions;

    @DynamoDBHashKey(attributeName="user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @DynamoDBRangeKey(attributeName="attribute")
    public String getAttribute() {
        return attribute;
    }

    public void setAttribute(String attribute) {
        this.attribute = attribute;
    }

    @DynamoDBAttribute(attributeName="value")
    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @DynamoDBAttribute(attributeName="updated")
    public Date getUpdated() {
        return updated;
    }

    public void setUpdated(Date updated) {
        this.updated = updated;
    }
    
    @DynamoDBAttribute(attributeName="permissions")
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public DataAttribute() {
        this.user = "";
        this.attribute = "";
        this.value = "";
        this.updated = new Date();
        this.permissions = new ArrayList<>();
        this.notFound = null;
        this.permissionDenied = null;
    }

    public DataAttribute(String user, String attribute, String value, Date updated, List<Permission> permissions) {
        this.user = user;
        this.attribute = attribute;
        this.value = value;
        this.updated = updated;
        this.permissions = permissions;
        this.notFound = null;
        this.permissionDenied = null;
    }

    public Boolean getNotFound() {
        return notFound;
    }

    public Boolean getPermissionDenied() {
        return permissionDenied;
    }

    public void setNotFound() {
        this.notFound = true;
        this.value = null;
        this.updated = null;
    }

    public void setPermissionDenied() {
        this.permissionDenied = true;
        this.value = null;
        this.updated = null;
    }

}
