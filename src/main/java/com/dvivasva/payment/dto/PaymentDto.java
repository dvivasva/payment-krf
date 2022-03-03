package com.dvivasva.payment.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PaymentDto {
    private String id;
    private double amount;
    private String payMode;
    private String numberPhoneOrAccount;

}
