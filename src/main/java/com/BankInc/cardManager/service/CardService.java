package com.BankInc.cardManager.service;

import com.BankInc.cardManager.entity.Card;

public interface CardService {
    Card generateCard(String id);
    Card activateCard(String id);
    String blockCard(String id);
    Card refillCard(String id, Integer balance);
    Integer getBalance (String id);
}
