package com.BankInc.cardManager.service.impl;

import com.BankInc.cardManager.entity.Transaction;
import com.BankInc.cardManager.repository.TransactionRepository;
import com.BankInc.cardManager.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Override
    public Transaction purchase(String cardId, Integer price) {
        LocalDate today = LocalDate.now();
        Transaction transaction = new Transaction(cardId, price, true, today);
        transactionRepository.save(transaction);
        return null;
    }

    @Override
    public Transaction getTransaction(Integer id) {
        if(transactionRepository.existsById(id)) {
            Transaction transaction = transactionRepository.findById(id).get();
            return transaction;
        } else {
            return null;
        }
    }
}
