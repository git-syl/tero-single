package com.oktfolio.tero.modules.sms.producer;

import com.oktfolio.tero.modules.mq.RocketProducer;
import com.oktfolio.tero.modules.sms.consumer.SmsMqConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
@Component
public class SmsMqProducer extends RocketProducer{

    private final static Logger logger = LoggerFactory.getLogger(SmsMqConsumer.class);

    private final static String TAG = "";
    private final static String TOPIC = "";
    private final static String GROUP_NAME = "";

    private DefaultMQProducer producer = new DefaultMQProducer(GROUP_NAME);

    public SmsMqProducer() {
        setProducer(producer);
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
        SendResult sendResult = sendNormalMessage(TOPIC, TAG, keys, content);
        logger.info("sendResult : {}", sendResult);
        producer.shutdown();
    }
}
