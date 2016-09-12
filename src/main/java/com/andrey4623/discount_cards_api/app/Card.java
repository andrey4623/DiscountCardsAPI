package com.andrey4623.discount_cards_api.app;

public class Card {
    private long number;
    private float value;
    private float discount;

    public Card() {
    }

    public Card(long number, float value, float discount) {
        this.number = number;
        this.value = value;
        this.discount = discount;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
}
