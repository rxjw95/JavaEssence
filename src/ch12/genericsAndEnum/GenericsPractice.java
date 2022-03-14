package ch12.genericsAndEnum;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class GenericsPractice {
    public static void main(String[] args) {
        Box<String> box = new Box<>();
        box.setItem("message");
        String msg = box.getItem();

        FruitBox<Fruit> fruitBox = new FruitBox<>();
        fruitBox.add(new Fruit()); // pass
        fruitBox.add(new Apple()); // pass

        FruitBox<Apple> appleBox = new FruitBox<>();
        JuiceMaker.<Apple>makeJuice(appleBox);
        JuiceMaker.<Fruit>makeJuice(fruitBox);
    }
}

class JuiceMaker {
    static Juice makeJuice(FruitBox<? extends Fruit> fruitBox) {
        return new Juice(fruitBox.getList().stream().map(Fruit::toString).collect(Collectors.joining(" ")));
    }
}

class Juice {
    String ingredient;
    public Juice(String ingredient) {
        this.ingredient = ingredient;
    }
}
class Box<T> {
    private T item;

    void setItem(T item) {
        this.item = item;
    }

    T getItem() {
        return item;
    }
}

class FruitBox<T extends Fruit> {
    private final List<T> list = new ArrayList<>();

    public void add(T fruit) {
        list.add(fruit);
    }

    public List<T> getList() {
        return list;
    }

    static <T> void sort(List<T> list, Comparator<? super T> comparator){}
}

class Fruit {
    private String color;

    String getColor() {
        return color;
    }

    @Override
    public String toString() {
        return color;
    }
}

class Apple extends Fruit {
}