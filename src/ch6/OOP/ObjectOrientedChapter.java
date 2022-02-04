package ch6.OOP;

import java.util.Arrays;

public class ObjectOrientedChapter {
    public static void main(String[] args) {
        Card firstCard = new Card();
        Card secondCard = new Card();

        firstCard.setNumber(7);
        firstCard.setPattern("diamond");

        secondCard.setNumber(1);
        secondCard.setPattern("clover");

        System.out.println(firstCard.getPattern() + " " + firstCard.getNumber());
        System.out.println(secondCard.getPattern() + " " + secondCard.getNumber());
        System.out.println(Card.width + " " + Card.height);
    }

    int sumCalculator(int... numbers) {
        return Arrays.stream(numbers).sum();
    }
}

class Card {
    private int number;
    private String pattern;

    static int width = 100;
    static int height = 250;

    public int getNumber() {
        return number;
    }

    public String getPattern() {
        return pattern;
    }

    void setNumber(int number) {
        this.number = number;
    }

    void setPattern(String pattern) {
        this.pattern = pattern;
    }
}
