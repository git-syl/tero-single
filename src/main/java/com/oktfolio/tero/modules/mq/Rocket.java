package com.oktfolio.tero.modules.mq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/16
 */
@Component
public class Rocket {

    private final static Logger logger = LoggerFactory.getLogger(Rocket.class);

    private final DefaultMQProducer producer;

    public Rocket(DefaultMQProducer producer) {
        this.producer = producer;
    }

    public SendResult sendNormalMessage(String topic,
                                        String tag,
                                        String keys,
                                        String content) {
        Message msg = new Message(topic, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        try {
            send = producer.send(msg);
            logger.info("topic : {}, tag : {}, keys : {}, content : {}", topic, tag, keys, content);
        } catch (MQClientException
                | RemotingException
                | MQBrokerException
                | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return send;
    }

    public SendResult sendSequenceMessage(String topic,
                                          String tag,
                                          String keys,
                                          String content) {
        Message msg = new Message(topic, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        try {
            send = producer.send(msg, (mqs, msg1, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, keys);
            logger.info("topic : {}, tag : {}, keys : {}, content : {}", topic, tag, keys, content);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        return send;
    }

    /**
     * 1  2  3   4   5  6  7  8  9  10 11 12 13 14  15  16  17 18
     * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     *
     * @param topic
     * @param tag
     * @param keys
     * @param content
     */
    public SendResult sendDelayMessage(String topic,
                                       String tag,
                                       String keys,
                                       String content,
                                       int timeLevel) {
        Message msg = new Message(topic, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        msg.setDelayTimeLevel(timeLevel);
        try {
            send = producer.send(msg);
            logger.info("topic : {}, tag : {}, keys : {}, content : {}", topic, tag, keys, content);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
        return send;
    }

    /**
     * 开源版不支持定时消息。
     *
     * @param topic
     * @param tag
     * @param keys
     * @param content
     * @param deliverTime
     * @return
     */
    // public SendResult sendTimingMessage(String topic,
    //                                    String tag,
    //                                    String keys,
    //                                    String content,
    //                                    long deliverTime) {
    //     Message msg = new Message(topic, tag, keys, content.getBytes(StandardCharsets.UTF_8));
    //     SendResult send = null;
    //     msg.setStartDeliverTime(timeStamp);
    //     try {
    //         send = producer.send(msg);
    //         logger.info("topic : {}, tag : {}, keys : {}, content : {}", topic, tag, keys, content);
    //     } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
    //         e.printStackTrace();
    //     }
    //     return send;
    // }
}
