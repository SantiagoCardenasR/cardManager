package com.BankInc.cardManager.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {
    private Integer id;
    private Integer transactionCardId;
    private Integer price;
    private Boolean state;
    private LocalDate purchaseDate;
}
