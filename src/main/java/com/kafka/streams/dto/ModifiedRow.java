package com.kafka.streams.dto;

public class ModifiedRow {
    private Schema schema;
    private Payload payload;

    public Schema getSchema() {
        return schema;
    }

    public void setSchema(Schema schema) {
        this.schema = schema;
    }

    public Payload getPayload() {
        return payload;
    }

    public void setPayload(Payload payload) {
        this.payload = payload;
    }

    @Override
    public String toString() {
        return "ModifiedRow{" +
                "schema=" + schema +
                ", payload=" + payload +
                '}';
    }
}
