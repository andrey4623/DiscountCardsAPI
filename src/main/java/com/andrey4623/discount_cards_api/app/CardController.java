package com.andrey4623.discount_cards_api.app;

import org.hibernate.Session;
import com.andrey4623.discount_cards_api.persistence.HibernateUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardController {

    /*
     * Get info about card.
     */
    @RequestMapping(value="/card", method = RequestMethod.GET)
    public ResponseEntity<Card> getCard(
            @RequestParam(value="number", defaultValue="0") long number) {

        // Validate input data.
        if (!Utils.isNumberValid(number)) {
            System.err.println("Exception: Card number is not valid.");
            return new ResponseEntity<Card>(HttpStatus.BAD_REQUEST);
        }

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
        } catch (Exception e) {
            System.err.println("Exception occurred while opening a session: " + e);
            return new ResponseEntity<Card>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Card card = null;

        // Retrieve a card.
        try {
            card = (Card) session.get(Card.class, number);
        } catch (Exception e) {
            System.err.println("Exception occurred while retrieving data: " + e);
            return new ResponseEntity<Card>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (card == null) {
            System.err.println("Card not found.");
            return new ResponseEntity<Card>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }

    /*
     * Create a new card or update an existing one.
     */
    @RequestMapping(value="/card", method = RequestMethod.POST)
    public ResponseEntity<Card> addCard(
            @RequestParam(value="number", defaultValue="0") long number,
            @RequestParam(value="value", defaultValue="0") float value,
            @RequestParam(value="discount", defaultValue="0") float discount) {

        // Validate input data.
        if (!Utils.isNumberValid(number)) {
            System.err.println("Exception: Card number is not valid.");
            return new ResponseEntity<Card>(HttpStatus.BAD_REQUEST);
        }
        if (!Utils.isValueValid(value)) {
            System.err.println("Exception: Card value is not valid.");
            return new ResponseEntity<Card>(HttpStatus.BAD_REQUEST);
        }
        if (!Utils.isDiscountValid(discount)) {
            System.err.println("Exception: Card discount is not valid.");
            return new ResponseEntity<Card>(HttpStatus.BAD_REQUEST);
        }

        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
        } catch (Exception e) {
            System.err.println("Exception occurred while opening a session: " + e);
            return new ResponseEntity<Card>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        Card card = null;

        // Retrieve a card.
        try {
            card = (Card) session.get(Card.class, number);
        } catch (Exception e) {
            System.err.println("Exception occurred while retrieving data: " + e);
            return new ResponseEntity<Card>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        /*
         * If there is no card with such number, create a new one; otherwise
         * update existing one.
         */

        if (card == null) {
            card = new Card(number, value, discount);
        } else {
            card.setValue(value);
            card.setDiscount(discount);
        }

        try {
            session.save(card);
            session.getTransaction().commit();
        } catch (Exception e) {
            System.err.println("Exception occurred while committing a transaction: " + e);
            return new ResponseEntity<Card>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Card>(card, HttpStatus.OK);
    }
}
