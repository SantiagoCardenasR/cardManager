package com.BankInc.cardManager.service.impl;

import com.BankInc.cardManager.entity.Card;
import com.BankInc.cardManager.entity.Transaction;
import com.BankInc.cardManager.repository.CardRepository;
import com.BankInc.cardManager.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements CardService {
    @Autowired
    public CardRepository cardRepository;
    @Override
    public Card generateCard(String id) {
        String tempCardNumber = id + randomNumberGenerator();
        Long cardNumber = Long.parseLong(tempCardNumber);
        LocalDate expirationDate = generateRandomExpirationDate();
        List<Transaction> transactions = new ArrayList<Transaction>();
        Card card = new Card(cardNumber, "", expirationDate, 0,false, transactions);
        cardRepository.saveAndFlush(card);
        return card;
    }

    @Override
    public Card activateCard(Integer id) {
        if(cardRepository.findById(id).isPresent()) {
            Card card = cardRepository.findById(id).get();
            card.setIsActive(true);
            cardRepository.save(card);
            return card;
        } else {
            return null;
        }
    }

    @Override
    public String blockCard(Integer id) {
        if(cardRepository.existsById(id)) {
            Card card = cardRepository.findById(id).get();
            card.setIsActive(false);
            cardRepository.save(card);
            return "Card is blocked";
        } else {
            return null;
        }
    }

    @Override
    public Card refillCard(Integer id, Integer balance) {
        if(cardRepository.existsById(id)) {
            Card card = cardRepository.findById(id).get();
            card.setBalance(card.getBalance() + balance);
            cardRepository.save(card);
            return card;
        } else {
            return null;
        }
    }

    @Override
    public Integer getBalance(Integer id) {
        if(cardRepository.existsById(id)) {
            Card card = cardRepository.findById(id).get();
            return card.getBalance();
        } else {
            return null;
        }
    }

    @Override
    public Card getCard(Integer id) {
        if(cardRepository.existsById(id)) {
            return cardRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public Integer updateBalance(Integer id, Integer balance) {
        if(cardRepository.existsById(id)) {
            Card card = cardRepository.findById(id).get();
            if(card.getBalance() > balance) {
                card.setBalance(card.getBalance() - balance);
                cardRepository.save(card);
                return card.getBalance();
            } else {
                return null;
            }
        } else {
            return null;
        }
    }


    public LocalDate generateRandomExpirationDate() {
        // Generate a random month between 1 (January) and 12 (December)
        int randomMonth = ThreadLocalRandom.current().nextInt(1, 13);

        // Generate a random year between the current year and the next 10 years
        int currentYear = LocalDate.now().getYear();
        int randomYear = ThreadLocalRandom.current().nextInt(currentYear, currentYear + 10);

        // Create a LocalDate object with the random month and year
        return LocalDate.of(randomYear, Month.of(randomMonth), 1)
                .plusMonths(1) // Adding one month to ensure the last day of the month
                .minusDays(1); // Set the last day of the month
    }

    public String randomNumberGenerator() {
        return IntStream.range(0, 10)
                .mapToObj(i -> String.valueOf(new Random().nextInt(10)))
                .collect(Collectors.joining());
    }
}
