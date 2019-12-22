package com.example.ebankingspg.java.model.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PurchaseDTO {

    private String type;
    private double amount;
    private String phoneNumber;
    private String rib;

}
