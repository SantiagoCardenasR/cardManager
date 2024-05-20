package com.BankInc.cardManager.controller;

import com.BankInc.cardManager.entity.Card;
import com.BankInc.cardManager.entity.dto.CancelPurchaseRequest;
import com.BankInc.cardManager.entity.dto.CardRefillRequest;
import com.BankInc.cardManager.entity.dto.TransactionResponse;
import com.BankInc.cardManager.service.CardService;
import com.BankInc.cardManager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/transaction")
public class TransactionController {
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CardService cardService;

    @PostMapping("/purchase")
    public ResponseEntity<?> purchase(@RequestBody CardRefillRequest cardRefillRequest) {
        Card card = cardService.getCard(cardRefillRequest.getCardId());
        if(card != null) {
            TransactionResponse newTransaction = transactionService.purchase(cardRefillRequest.getCardId(), cardRefillRequest.getBalance());
           if(newTransaction != null) {
               Integer newBalance = cardService.updateBalance(cardRefillRequest.getCardId(), cardRefillRequest.getBalance());
               if(newBalance != null) {
                   return ResponseEntity.ok(newTransaction);
               } else {
                   return ResponseEntity.badRequest().body("Fondos insuficientes");
               }
           } else {
               return ResponseEntity.badRequest().body("Su tarjeta está bloqueada, contáctese con su entidad financiera");
           }
        } else {
            return ResponseEntity.badRequest().body("Tarjeta inválida");
        }

    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<TransactionResponse> getTransaction(@PathVariable Integer transactionId) {
        TransactionResponse transaction = transactionService.getTransaction(transactionId);
        if(transaction != null) {
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/anulation")
    public ResponseEntity<TransactionResponse> cancelPurchase(@RequestBody CancelPurchaseRequest cancelPurchaseRequest) {
        TransactionResponse transaction = transactionService.cancelPurchase(cancelPurchaseRequest.getCardId(), cancelPurchaseRequest.getTransactionId());
        if(transaction != null) {
            cardService.refillCard(cancelPurchaseRequest.getCardId(), transaction.getPrice());
            return ResponseEntity.ok(transaction);
        } else {
            return ResponseEntity.noContent().build();
        }
    }
}
