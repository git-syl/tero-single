package com.oktfolio.tero.modules.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/16
 */
@Service
public class RocketMq {

    public SendResult sendNormalMessage(DefaultMQProducer producer,
                                        String top,
                                        String tag,
                                        String keys,
                                        String content) {
        Message msg = new Message(top, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        try {
            send = producer.send(msg);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
        return send;
    }

    public SendResult sendSequenceMessage(DefaultMQProducer producer,
                                          String top,
                                          String tag,
                                          String keys,
                                          String content) {
        Message msg = new Message(top, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        try {
            send = producer.send(msg, (mqs, msg1, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, keys);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
        return send;
    }

    /**
     * 1  2  3   4   5  6  7  8  9  10 11 12 13 14  15  16  17 18
     * 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
     *
     * @param producer
     * @param top
     * @param tag
     * @param keys
     * @param content
     */
    public SendResult sendDelayMessage(DefaultMQProducer producer,
                                 String top,
                                 String tag,
                                 String keys,
                                 String content,
                                 int timeLevel) {
        Message msg = new Message(top, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        SendResult send = null;
        msg.setDelayTimeLevel(timeLevel);
        try {
            send = producer.send(msg);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
        return send;
    }
}
