package com.BankInc.cardManager.controller;

import com.BankInc.cardManager.entity.Card;
import com.BankInc.cardManager.entity.Transaction;
import com.BankInc.cardManager.service.CardService;
import com.BankInc.cardManager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CardService cardService;

    @PostMapping("/purchase")
    public ResponseEntity<Transaction> purchase(@RequestBody String cardId, Integer price) {
        Card card = cardService.getCard(cardId);
        if(card != null) {
           Integer newBalance = cardService.updateBalance(cardId, price);
           if(newBalance != null) {
              Transaction newTransaction = transactionService.purchase(cardId, price);
               return ResponseEntity.ok(newTransaction);
           } else {
               return ResponseEntity.noContent().build();
           }
        } else {
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<Transaction> getTransaction(@PathVariable Integer transactionId) {
        Transaction transaction = transactionService.getTransaction(transactionId);
        if(transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
