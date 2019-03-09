package com.kafka.streams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.streams.dto.ModifiedRow;
import com.kafka.streams.dto.Payload;

import java.io.File;
import java.io.IOException;

public class ObjectMapperDemo {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        ModifiedRow modifiedRow = objectMapper.readValue(new File("F:\\schema.json"), ModifiedRow.class);
        System.out.println(modifiedRow.getPayload().getXml_content());

        String jsonInString = objectMapper.writeValueAsString(modifiedRow);
        System.out.println(jsonInString);
    }
}
