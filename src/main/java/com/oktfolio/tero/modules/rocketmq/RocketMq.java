package com.oktfolio.tero.modules.rocketmq;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/16
 */
public class RocketMq {

    public void sendSeqMessage(DefaultMQProducer producer,
                               String top,
                               String tag,
                               String keys,
                               String content) {
        Message msg = new Message(top, tag, keys, content.getBytes(StandardCharsets.UTF_8));
        try {
            // 创建一个自定义消息队列选择器
            producer.send(msg, (mqs, msg1, arg) -> {
                Integer id = (Integer) arg;
                int index = id % mqs.size();
                return mqs.get(index);
            }, keys);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
