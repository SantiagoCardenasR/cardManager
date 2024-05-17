package com.BankInc.cardManager.controller;

import com.BankInc.cardManager.entity.Card;
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

    @GetMapping("/{id}/number")
    public ResponseEntity<Card> generateCard(@PathVariable String id) {
        Card response = cardService.generateCard(id);
        if( response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/card/enroll")
    public ResponseEntity<Card> activateCard(@RequestBody String cardId) {
        Card response = cardService.activateCard(cardId);
        if(response != null) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> blockCard(@PathVariable String id) {
        return ResponseEntity.ok(cardService.blockCard(id));
    }
}