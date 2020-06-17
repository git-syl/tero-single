package com.oktfolio.tero.modules.mq;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.concurrent.Future;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/16
 */
public class Kafka {

    private final Producer<String, String> producer;

    public Kafka(Producer<String, String> producer) {
        this.producer = producer;
    }

    public void send(String topic, String key, String value) {
        Future<RecordMetadata> future = producer.send(new ProducerRecord<>(topic, key, value));
    }
}
