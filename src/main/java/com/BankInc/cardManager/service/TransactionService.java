package com.BankInc.cardManager.service;

import com.BankInc.cardManager.entity.Transaction;
import com.BankInc.cardManager.entity.dto.TransactionResponse;

public interface TransactionService {
    TransactionResponse purchase(Integer cardId, Integer price);
    TransactionResponse getTransaction(Integer id);
    TransactionResponse cancelPurchase(Integer cardId, Integer transactionId);
}
