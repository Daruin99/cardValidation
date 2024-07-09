package com.cardValidation.cardValidation.controller;

import com.cardValidation.cardValidation.domain.Card;
import com.cardValidation.cardValidation.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/card-validation")
public class CardValidationController {

    private CardService cardService;

    @Autowired
    public CardValidationController(CardService cardService) {
        this.cardService = cardService;
    }

    @PostMapping("/validate")
    public ResponseEntity<String> validateCard(@RequestBody Card cardDetails) {
        Card card = cardService.findByCardNumber(cardDetails.getCardNumber());
        if (card != null && card.getExpiryDate().equals(cardDetails.getExpiryDate()) && card.getCvv().equals(cardDetails.getCvv())) {
            if (cardService.isExpiryDateValid(card)) {
                return ResponseEntity.ok("Valid");
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Card Date is expired");
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid Card Details");
    }
}
