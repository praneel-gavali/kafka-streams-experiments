package com.kafka.streams.dto;

import java.util.List;

public class Schema {
    private String type;
    private List<Field> fields;
    private Boolean optional;
    private String name;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Field> getFields() {
        return fields;
    }

    public void setFields(List<Field> fields) {
        this.fields = fields;
    }

    public Boolean getOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Schema{" +
                "type='" + type + '\'' +
                ", fields=" + fields +
                ", optional=" + optional +
                ", name='" + name + '\'' +
                '}';
    }
}
