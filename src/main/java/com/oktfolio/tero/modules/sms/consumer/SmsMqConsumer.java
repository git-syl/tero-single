package com.oktfolio.tero.modules.sms.consumer;

import com.oktfolio.tero.utils.ThreadPoolUtils;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

import java.util.List;
import java.util.concurrent.ExecutorService;

/**
 * @author oktfolio oktfolio@gmail.com
 * @date 2020/06/15
 */
public class SmsMqConsumer {

    private final static Logger logger = LoggerFactory.getLogger(SmsMqConsumer.class);

    private final static String TAG = "";
    private final static String TOPIC = "";
    private final static String GROUP_NAME = "";

    @EventListener(ContextRefreshedEvent.class)
    public void execute() {
        listen();
    }

    private void listen() {
        // Instantiate with specified consumer group name.
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(GROUP_NAME);
        // Specify name server addresses.
        consumer.setNamesrvAddr("localhost:9876;localhost:9876");
        // Subscribe one more more topics to consume.
        try {
            consumer.subscribe(TOPIC, TAG);
        } catch (MQClientException e) {
            logger.error(e.getErrorMessage());
        }
        // Register callback to execute on arrival of messages fetched from brokers.
        consumer.registerMessageListener(this::consumeMessage);
        // Launch the consumer instance.
        try {
            consumer.start();
        } catch (MQClientException e) {
            logger.error(e.getErrorMessage());
        }
        logger.info("Sms Consumer Started.%n");
    }

    ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> msgs,
                                             ConsumeConcurrentlyContext context) {
        for (MessageExt msg : msgs) {
            ExecutorService executorService = ThreadPoolUtils.getExecutorService();
            executorService.submit(() -> System.out.println(msg));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }

}
