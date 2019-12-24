package com.example.ebankingspg.java.model.dto;

import com.example.ebankingspg.java.model.Transaction;
import com.example.ebankingspg.java.request.CreateTransactionRequest;
import lombok.*;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneVerificationVerifyTransactionDTO {
    @NotNull
    private String phoneNumber;
    @NotNull
    private String countryCode;
    @NotNull
    private String token;

    private CreateTransactionRequest transaction;

}
