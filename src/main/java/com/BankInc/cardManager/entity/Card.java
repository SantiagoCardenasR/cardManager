package com.BankInc.cardManager.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Card {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private Long cardNumber;
    private String holderName;
    private LocalDate expirationDate;
    private Integer balance;
    private Boolean isActive;
    @OneToMany(mappedBy = "card", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    public Card(Long pCardNumber, String pHolderName, LocalDate pExpirationDate, Integer pBalance, Boolean pIsActive, List<Transaction> pTransactions) {
        cardNumber = pCardNumber;
        holderName = pHolderName;
        expirationDate = pExpirationDate;
        balance = pBalance;
        isActive = pIsActive;
        transactions = pTransactions;
    }
}
