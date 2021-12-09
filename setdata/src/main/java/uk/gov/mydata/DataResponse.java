package uk.gov.mydata;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DataResponse implements Serializable {
    private List<DataAttribute> attributes;

    public List<DataAttribute> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<DataAttribute> attributes) {
        this.attributes = attributes;
    }

    public DataResponse() {
        this.attributes = new ArrayList<>();
    }

    public DataResponse(List<DataAttribute> attributes) {
        this.attributes = attributes;
    }
    public void addAttribute(DataAttribute attribute) {
        this.attributes.add(attribute);
    }

}
