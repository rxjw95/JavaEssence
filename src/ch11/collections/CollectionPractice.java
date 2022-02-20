package ch11.collections;

import java.util.*;

public class CollectionPractice {
    public static void main(String[] args) {
        //setTest();
        treeSetTest();
    }

    public static void treeSetTest() {
        TreeSet<String> treeSet = new TreeSet<>(Arrays.asList("abc", "Car", "ab", "aBc", "basic", "dslr", "ship"));
        final String from = "a";
        final String to = "dslr";

        System.out.println(treeSet.subSet(from, to)); // [aBc, ab, abc, basic]
        System.out.println(treeSet.subSet(from, true, to, true)); // [aBc, ab, abc, basic, dslr]

        TreeSet<Integer> integerSet = new TreeSet<>(Arrays.asList(80, 40, 25, 100, 45, 70, 85, 55, 50));

        System.out.println(integerSet.tailSet(50));
        System.out.println(integerSet.headSet(55));
    }

    public static void setTest() {
        Person man1 = new Person("woogie", 28);
        Person man2 = new Person("woogie", 28);

        Set<Person> set = new HashSet<>();
        set.add(man1);
        set.add(man2);

        set.forEach(System.out::println); // Person{name='woogie', age=28}
    }
}

class Person {
    String name;
    int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;
        return age == person.age && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age);
    }
}
