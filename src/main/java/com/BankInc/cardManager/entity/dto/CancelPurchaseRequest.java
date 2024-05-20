package com.BankInc.cardManager.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CancelPurchaseRequest {
    private Integer cardId;
    private Integer transactionId;
}
