package com.BankInc.cardManager.controller;

import com.BankInc.cardManager.entity.Card;
import com.BankInc.cardManager.entity.dto.CardId;
import com.BankInc.cardManager.entity.dto.CardRefillRequest;
import com.BankInc.cardManager.entity.dto.CardResponse;
import com.BankInc.cardManager.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/card")
public class CardController {
    @Autowired
    private CardService cardService;

    @GetMapping("/{productId}/number")
    public ResponseEntity<CardResponse> generateCard(@PathVariable String productId) {
        Card response = cardService.generateCard(productId);
        if( response != null) {
            return ResponseEntity.ok(new CardResponse(response.getCardNumber(),response.getHolderName(),response.getExpirationDate(),response.getBalance(), response.getIsActive()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/enroll")
    public ResponseEntity<CardResponse> activateCard(@RequestBody CardId cardId) {
        Card response = cardService.activateCard(cardId.getId());
        if(response != null) {
            return ResponseEntity.ok(new CardResponse(response.getCardNumber(),response.getHolderName(),response.getExpirationDate(),response.getBalance(), response.getIsActive()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{cardId}")
    public ResponseEntity<String> blockCard(@PathVariable Integer cardId) {
        return ResponseEntity.ok(cardService.blockCard(cardId));
    }

    @PostMapping("/balance")
    public ResponseEntity<CardResponse> refillCard(@RequestBody CardRefillRequest cardRefillRequest) {
        Card response = cardService.refillCard(cardRefillRequest.getCardId(), cardRefillRequest.getBalance());
        if(response != null) {
            return ResponseEntity.ok(new CardResponse(response.getCardNumber(),response.getHolderName(),response.getExpirationDate(),response.getBalance(), response.getIsActive()));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/balance/{cardId}")
    public ResponseEntity<Integer> getBalance(@PathVariable Integer cardId) {
        Integer response = cardService.getBalance(cardId);
        if(response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }
}
