package com.oktfolio.tero.modules.sms.producer;

import com.oktfolio.tero.modules.rocketmq.RocketMq;
import com.oktfolio.tero.modules.sms.consumer.SmsMqConsumer;
import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
@Component
public class SmsMqProducer {

    private final static Logger logger = LoggerFactory.getLogger(SmsMqConsumer.class);

    private final static String TAG = "";
    private final static String TOPIC = "";
    private final static String GROUP_NAME = "";

    private final RocketMq rocketMq;

    public SmsMqProducer(RocketMq rocketMq) {
        this.rocketMq = rocketMq;
    }

    public void produce() {

        String content = "";
        String keys = "";

        DefaultMQProducer producer = new DefaultMQProducer(GROUP_NAME);
        // Specify name server addresses.
        producer.setNamesrvAddr("localhost:9876;localhost:9876");
        // Launch the instance.
        try {
            producer.start();
        } catch (MQClientException e) {
            logger.error(e.getMessage());
        }
        // Create a message instance, specifying topic, tag and message body.
        Message msg = new Message(TOPIC, TAG, keys, content.getBytes(StandardCharsets.UTF_8));
        // Call send message to deliver message to one of brokers.
        SendResult sendResult = null;
        try {
            sendResult = producer.send(msg);
        } catch (MQClientException | RemotingException | MQBrokerException | InterruptedException e) {
            logger.error(e.getMessage());
        }
        logger.info("sendResult : {}", sendResult);
        //Shut down once the producer instance is not longer in use.
        producer.shutdown();
    }
}
