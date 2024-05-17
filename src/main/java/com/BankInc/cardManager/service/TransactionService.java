package com.BankInc.cardManager.service;

import com.BankInc.cardManager.entity.Transaction;

public interface TransactionService {
    Transaction purchase(String cardId, Integer price);
    Transaction getTransaction(Integer id);
}
