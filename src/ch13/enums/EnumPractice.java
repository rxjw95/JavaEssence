package ch13.enums;

public class EnumPractice {
    public static void main(String[] args) {
        System.out.println(TRANSPORTATION.TRAIN.getBasicFare()); // 150
        System.out.println(TRANSPORTATION.TRAIN.getAmountFor(100)); // 15000
        System.out.println(TRANSPORTATION.BUS.getBasicFare()); // 100
        System.out.println(TRANSPORTATION.BUS.getAmountFor(150)); // 15000
    }
}

enum TRANSPORTATION {
    BUS(100) {
        @Override
        int getAmountFor(int distance) {
            return getBasicFare() * distance;
        }
    },
    TRAIN(150) {
        @Override
        int getAmountFor(int distance) {
            return getBasicFare() * distance;
        }
    },
    SHIP(100) {
        @Override
        int getAmountFor(int distance) {
            return getBasicFare() * distance;
        }
    },
    AIRPLANE(300) {
        @Override
        int getAmountFor(int distance) {
            return getBasicFare() * distance;
        }
    };

    private final int basicFare;

    TRANSPORTATION(int basicFare) {
         this.basicFare = basicFare;
    }

    abstract int getAmountFor(int distance);

    public int getBasicFare() {
        return basicFare;
    }
}
