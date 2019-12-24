package com.example.ebankingspg.java.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PhoneVerificationStartDTO {

    @NotNull
    private String phoneNumber;
    @NotNull
    private String countryCode;
    @NotNull
    private String via;

}
