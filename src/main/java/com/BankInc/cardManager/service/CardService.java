package com.BankInc.cardManager.service;

import com.BankInc.cardManager.entity.Card;

public interface CardService {
    Card generateCard(String id);
    Card activateCard(Integer id);
    String blockCard(Integer id);
    Card refillCard(Integer id, Integer balance);
    Integer getBalance (Integer id);
    Card getCard(Integer id);
    Integer updateBalance(Integer id, Integer balance);
}
