package com.BankInc.cardManager.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {
    private Long CardNumber;
    private String HolderName;
    private LocalDate expirationDate;
    private Integer balance;
    private Boolean isActive;
}
