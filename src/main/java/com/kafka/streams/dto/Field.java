package com.kafka.streams.dto;

public class Field {
    private String type;
    private Boolean optional;
    private String field;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Boolean isOptional() {
        return optional;
    }

    public void setOptional(Boolean optional) {
        this.optional = optional;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    @Override
    public String toString() {
        return "Field{" +
                "type='" + type + '\'' +
                ", optional=" + optional +
                ", field='" + field + '\'' +
                '}';
    }
}
