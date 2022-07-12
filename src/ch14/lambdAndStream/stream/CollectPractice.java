package ch14.lambdAndStream.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

class Age {
    private int value;

    public static Age generateAge(int value) {
        return new Age(value);
    }

    private Age(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Age{" +
                "value=" + value +
                '}';
    }
}

public class CollectPractice {
    public static Stream<String> generateStream() {
        return Stream.of("1", "2", "3");
    }

    public static void main(String[] args) {
        List<String> stringList = generateStream().collect(toList());
        generateStream().collect(toSet());
        Map<Integer, String> stringMap = generateStream().collect(toMap(Integer::parseInt, Function.identity()));
        generateStream().collect(toCollection(HashSet::new));
        String[] stringArray = generateStream().toArray(String[]::new);


        List<Age> ages = List.of(Age.generateAge(12), Age.generateAge(17), Age.generateAge(28));

        System.out.println(ages.stream().reduce(Age.generateAge(0), (a, b) -> Age.generateAge(a.getValue() + b.getValue())));
        System.out.println(ages.stream().collect(counting()));
        System.out.println(ages.stream().collect(summingInt(Age::getValue)));
        System.out.println(ages.stream().collect(averagingInt(Age::getValue)));
        System.out.println(ages.stream().collect(collectingAndThen(maxBy(Comparator.comparingInt(Age::getValue)), Optional::get)).getValue());
        System.out.println(ages.stream().collect(maxBy(Comparator.comparingInt(Age::getValue))).get().getValue());
        System.out.println(ages.stream().collect(minBy(Comparator.comparingInt(Age::getValue))).get().getValue());
        System.out.println(ages.stream().collect(summarizingInt(Age::getValue)));

        System.out.println(ages.stream()
                .map(age -> age.getValue())
                .map(intAge -> Integer.toString(intAge))
                .collect(joining(", ", "[", "]")));

        List<Person> people = Person.makePeople();

        Map<Boolean, List<Person>> adultMap = people.stream().collect(partitioningBy(person -> person.getAge() > 20));
        Map<Boolean, Person> maxAgeGroupBasedOnAdult = people.stream().collect(
                partitioningBy(
                        person -> person.getAge() > 20,
                        collectingAndThen(
                                maxBy(Comparator.comparingInt(Person::getAge)),
                                Optional::get)
                )
        );

        Map<String, List<Person>> groupBySex = people.stream().collect(groupingBy(Person::getSex));
        Map<String, Double> averageAgeGroupBySex = people.stream().collect(
                groupingBy(
                        Person::getSex,
                        averagingInt(Person::getAge)
                )
        );

    }
}
