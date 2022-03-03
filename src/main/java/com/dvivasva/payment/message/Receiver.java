package com.dvivasva.payment.message;

import com.dvivasva.payment.dto.PaymentDto;
import com.dvivasva.payment.entity.RequestBuyBootCoin;
import com.dvivasva.payment.service.PaymentService;
import com.dvivasva.payment.utils.JsonUtils;
import com.dvivasva.payment.utils.Topic;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;

@RequiredArgsConstructor
@Service
public class Receiver {

    private static final Logger logger = LoggerFactory.getLogger(Receiver.class);

    private final PaymentService paymentService;

    private final Sender sender;

    @KafkaListener(topics = Topic.REQUEST_BUY, groupId = "group_id_payment")
    public void consumeFormGateway(String param) {

        logger.info("Has been published an insert payment from service gateway-mobile : " + param);
        var value = new RequestBuyBootCoin();
        PaymentDto paymentDto = null;
        try {
            value = JsonUtils.convertFromJsonToObject(param, RequestBuyBootCoin.class);
            paymentDto = new PaymentDto();
            paymentDto.setAmount(value.getAmount());
            paymentDto.setPayMode(value.getPayMode());
            paymentDto.setNumberPhoneOrAccount(value.getNumber());

        } catch (IOException e) {
            e.printStackTrace();
        }
        createPayment(paymentDto);

    }

    public void createPayment(PaymentDto paymentDto) {
        var result = Mono.just(paymentDto);
        paymentService.create(result).doOnNext(p -> logger.info("registry success" + p))
                .subscribe();
    }


    @KafkaListener(topics = Topic.RESPONSE_WALLET, groupId = "group_id_payment")
    public void consumeFormWalletResponse(String param) {
        logger.info("Has been published an GET WALLET from service wallet-krf : " + param);
    }

    @KafkaListener(topics = Topic.RESPONSE_ACCOUNT_ORIGIN, groupId = "group_id_payment")
    public void consumeFormAccount(String param) {
        logger.info("Has been published an GET ACCOUNT from service account-ktf : " + param);
    }

    @KafkaListener(topics = Topic.RESPONSE_ACCOUNT_FROM_WALLET, groupId = "group_id_payment")
    public void consumeFormWallet(String param) {
        logger.info("Has been published an GET ACCOUNT from service wallet-krf : " + param);
    }


}
