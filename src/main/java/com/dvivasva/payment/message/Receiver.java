package com.dvivasva.payment.message;

import com.dvivasva.payment.service.PaymentService;
import com.dvivasva.payment.utils.Topic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private final PaymentService paymentService;
    private final Sender kafkaProducer;

    @KafkaListener(topics = Topic.REQUEST_BUY, groupId = "group_id_gateway")
    public void consumeFormGateway(String param) {
        logger.info("Has been published an insert payment from service gateway-mobile : " + param);

    }
}
