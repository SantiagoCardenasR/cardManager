package com.BankInc.cardManager.service.impl;

import com.BankInc.cardManager.entity.Card;
import com.BankInc.cardManager.entity.Transaction;
import com.BankInc.cardManager.entity.dto.TransactionResponse;
import com.BankInc.cardManager.repository.CardRepository;
import com.BankInc.cardManager.repository.TransactionRepository;
import com.BankInc.cardManager.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    @Autowired
    TransactionRepository transactionRepository;
    @Autowired
    CardRepository cardRepository;
    @Override
    public TransactionResponse purchase(Integer cardId, Integer price) {
        if(cardRepository.findById(cardId).isPresent()) {
            Card card = cardRepository.findById(cardId).get();
            if(card.getIsActive()) {
                LocalDate today = LocalDate.now();
                Transaction transaction = new Transaction(cardId, price, true, today, card);
                List<Transaction> tempTransactions = card.getTransactions();
                tempTransactions.add(transaction);
                card.setTransactions(tempTransactions);
                transactionRepository.save(transaction);
                cardRepository.save(card);
                return new TransactionResponse(transaction.getId(), transaction.getTransactionCardId(),transaction.getPrice(),transaction.getState(),transaction.getPurchaseDate());
            } else {
                return null;
            }
        } else {
            return null;
        }

    }

    @Override
    public TransactionResponse getTransaction(Integer id) {
        if(transactionRepository.findById(id).isPresent()) {
            Transaction transaction = transactionRepository.findById(id).get();
            return new TransactionResponse(transaction.getId(), transaction.getTransactionCardId(),transaction.getPrice(),transaction.getState(),transaction.getPurchaseDate());
        } else {
            return null;
        }
    }

    @Override
    public TransactionResponse cancelPurchase(Integer cardId, Integer transactionId) {
        if(transactionRepository.existsById(transactionId)) {
            Transaction transaction = transactionRepository.findById(transactionId).get();
            LocalDate today = LocalDate.now();
            LocalDate yesterday = today.minusDays(1);
            if(!transaction.getPurchaseDate().isBefore(yesterday)) {
                transaction.setState(false);
                transactionRepository.save(transaction);
                return new TransactionResponse(transaction.getId(), transaction.getTransactionCardId(),transaction.getPrice(),transaction.getState(),transaction.getPurchaseDate());
            }
        }
        return null;
    }


}
