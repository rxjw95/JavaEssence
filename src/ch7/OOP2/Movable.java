package ch7.OOP2;

public interface Movable {
    int a = 0; // Need to initialize

    // `default` keyword is required, when implementing the inside of a method
    default void move() {
        System.out.println("move");
    }
}
