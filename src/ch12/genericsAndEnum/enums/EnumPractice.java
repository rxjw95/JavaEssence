package ch12.genericsAndEnum.enums;

import java.util.Arrays;

public class EnumPractice {
    public static void main(String[] args) {
        System.out.println(FRUIT.getPriceBy("banana")); // 1000
    }
}

enum FRUIT {
    APPLE(500),
    BANANA(1000),
    MELON(1500);

    private final int price;

    FRUIT(int price) {
        this.price = price;
    }

    public static int getPriceBy(String fruit) {
        return Arrays.stream(values())
                .filter(value -> value.name().equalsIgnoreCase(fruit))
                .map(filtered -> filtered.price)
                .findAny()
                .orElse(100);
    }
}
