package com.kafka.streams;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kafka.streams.dto.ModifiedRow;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;

import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;

public class ValueTransformationDemoApp {
    private static final String CONNECTOR_ID = "test-connector";

    public static void main(String[] args) {


        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "value-tranform-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "PLAINTEXT://192.168.29.74:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
//        config.put(ConnectorConfig.NAME_CONFIG, CONNECTOR_ID);

        final StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> stream = builder.stream("test-sqlite-jdbc-accounts");
        KStream<String, String> transformedValue = stream.mapValues(value -> {
                    System.out.println(value);
//                    ObjectMapper objectMapper = new ObjectMapper();
//                    ModifiedRow modifiedRow  = null;
//                    try {
//                        modifiedRow = objectMapper.readValue(value, ModifiedRow.class);
//                        String jsonInString = objectMapper.writeValueAsString(modifiedRow);
//
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    if (value.equalsIgnoreCase("hello")) {
//                        return "BYE";
//                    } else {
//                        return value;
//                    }
                    return value;
                }
        );

//        KStream<String, String> transformedValue = stream.flatMapValues(value -> Arrays.asList(value.split("\\W+")));
        transformedValue.to("accounts_modified", Produced.with(Serdes.String(), Serdes.String()));
        final KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();
        System.out.println(streams.toString());
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
