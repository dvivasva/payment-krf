package com.dvivasva.payment.message;

import com.dvivasva.payment.entity.Payment;
import com.dvivasva.payment.utils.Topic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class Sender {


    private final KafkaTemplate<String, Payment> kafkaTemplate;
    private static final Logger logger = LoggerFactory.getLogger(Sender.class);

    public void sendResponsePayment(Payment value) {
        kafkaTemplate.send(Topic.RESPONSE_PAYMENT,value);
        logger.info("Messages successfully pushed on topic: " + Topic.RESPONSE_PAYMENT);
    }

}
