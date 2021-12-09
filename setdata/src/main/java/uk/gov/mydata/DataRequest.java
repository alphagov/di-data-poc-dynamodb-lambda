package uk.gov.mydata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataRequest implements Serializable {
    private String user;
    private String service;
    private Boolean read;
    private Boolean write;
    private List<DataAttribute> attributes;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public Boolean getRead() {
        return read;
    }

    public void setRead(Boolean read) {
        this.read = read;
    }

    public Boolean getWrite() {
        return write;
    }

    public void setWrite(Boolean write) {
        this.write = write;
    }

    public List<DataAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DataAttribute> attributes) {
        this.attributes = attributes;
    }

    public void addAttribute(DataAttribute attribute) {
        this.attributes.add(attribute);
    }

    public DataRequest() {
        this.user = "";
        this.service = "";
        this.read = false;
        this.write = false;
        this.attributes = new ArrayList<>();
    }

    public DataRequest(String user, String service, Boolean read, Boolean write, List<DataAttribute> attributes) {
        this.user = user;
        this.service = service;
        this.read = read;
        this.write = write;
        this.attributes = attributes;
    }
    
}
