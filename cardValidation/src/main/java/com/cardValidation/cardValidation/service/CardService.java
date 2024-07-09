package com.cardValidation.cardValidation.service;

import com.cardValidation.cardValidation.domain.Card;
import com.cardValidation.cardValidation.repository.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Service
public class CardService {

    private CardRepository cardRepository;

    @Autowired
    public CardService(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

    public Card findByCardNumber(String cardNumber) {
        return cardRepository.findByCardNumber(cardNumber);
    }
    public boolean isExpiryDateValid(Card card) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");
        YearMonth expiry = YearMonth.parse(card.getExpiryDate(), formatter);
        YearMonth current = YearMonth.now();
        return expiry.isAfter(current);    }
}
