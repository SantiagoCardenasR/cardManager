package com.BankInc.cardManager.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String transactionCardId;
    private Integer price;
    private Boolean state;
    private LocalDate purchaseDate;
    @ManyToOne
    @JoinColumn(name = "cardId")
    private Card card;

    public Transaction(String pCardId, Integer pPrice, Boolean pState, LocalDate pPurchaseDate) {
        transactionCardId = pCardId;
        price = pPrice;
        state = pState;
        purchaseDate = pPurchaseDate;
    }
}
