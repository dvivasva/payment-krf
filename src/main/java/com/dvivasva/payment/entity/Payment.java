package com.dvivasva.payment.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("payment-krf")
public class Payment{
    @Id
    private String id;
    private double amount;
    private String payMode;
    private String numberPhoneOrAccount;
}
