package com.oktfolio.tero.modules.dial.producer;

import com.oktfolio.tero.modules.mq.Kafka;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public class DialMqProducer {

    public void produce() {

        Properties props = new Properties();
        props.put("bootstrap.servers", "localhost:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");

        Producer<String, String> producer = new KafkaProducer<>(props);

        Kafka kafka = new Kafka(producer);

        String topic = "";
        String key = "";
        String value = "";
        kafka.send(topic, key, value);
    }
}
