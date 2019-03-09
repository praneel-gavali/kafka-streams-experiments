package com.kafka.streams;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;

import java.util.Arrays;
import java.util.Properties;

public class WordCountApp {

    public static void main(String[] args) {
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "wordcount-application");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "PLAINTEXT://192.168.29.74:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());

        final StreamsBuilder builder = new StreamsBuilder();
        KStream<String, String> stream = builder.stream("word-count-input");
        KTable<String, Long> wordCount = stream.mapValues(value -> value.toLowerCase())
                .flatMapValues(value -> Arrays.asList(value.split(" ")))
                .selectKey((key, value) -> value)
                .groupByKey()
                .count();
        wordCount.toStream().to("word-count-output", Produced.with(Serdes.String(), Serdes.Long()));

        final KafkaStreams streams = new KafkaStreams(builder.build(), config);
        streams.start();

        System.out.println(streams.toString());

        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));

    }
}
