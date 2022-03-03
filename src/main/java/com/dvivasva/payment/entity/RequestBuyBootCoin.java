package com.dvivasva.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestBuyBootCoin {
    private double amount;
    private String payMode;
    private String number;
}
