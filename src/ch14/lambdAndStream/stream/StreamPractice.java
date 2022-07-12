package ch14.lambdAndStream.stream;


import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.stream.Stream;

enum Predicates{
    EVEN(i -> i % 2 == 0), ODD(i -> i % 2 != 0);

    private final Predicate<Integer> predicate;

    Predicates(Predicate<Integer> predicate) {
        this.predicate = predicate;
    }

    public Predicate<Integer> match() {
        return predicate;
    }
}
class StreamSet {
    public Stream<Integer> evenIterate() {
        return Stream.iterate(0, n -> n + 2);
    }

    public Stream<Integer> oddIterate() {
        return Stream.iterate(1, n -> n + 2);
    }

    public Stream<Integer> randomGenerate(int begin, int end) {
        return Stream.generate(() -> {
            try {
                return SecureRandom.getInstanceStrong().nextInt(begin, end);
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        });
    }

    public Stream<Integer> skip() {
        Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5);
        return intStream.skip(3);
    }

    public Stream<Integer> limit() {
        Stream<Integer> intStream = Stream.of(1, 2, 3, 4, 5);
        return intStream.limit(10);
    }

    public Stream<Integer> distinct() {
        Stream<Integer> intStream = Stream.of(1, 1, 1, 2, 2, 3, 3, 3);
        return intStream.distinct();
    }

    public Stream<Integer> filter() {
        Stream<Integer> intStream = Stream.of(1, 2, 3);
        return intStream.filter(Predicates.EVEN.match());
    }

    public Stream<String> list() {
        return Stream.of("a", "c", "z", "d", "x", "AA", "Ca", "ac").sorted(Comparator.reverseOrder());
    }

    public Stream<Integer> map(){
        return Stream.of("1", "2", "3")
                .map(Integer::parseInt)
                .peek(System.out::println)
                .filter(Predicates.ODD.match());
    }

    public Stream<String> flatMap() {
        return Stream.of(new String[]{"a", "c", "z", "d"}, new String[]{"x", "AA", "Ca", "ac"})
                .flatMap(Arrays::stream)
                .map(str -> str.substring(0, 1));
    }
}

public class StreamPractice {
    public static void main(String[] args) {
        StreamSet streamSet = new StreamSet();

        streamSet.evenIterate()
                .limit(10)
                .forEach(System.out::println);

        streamSet.oddIterate()
                .limit(10)
                .forEach(System.out::println);

        streamSet.randomGenerate(0, 10)
                .limit(10)
                .forEach(System.out::println);
        System.out.println("--");
        streamSet.skip()
                .forEach(System.out::println);

        System.out.println("--");

        streamSet.limit()
                .forEach(System.out::println);

        streamSet.distinct()
                .forEach(System.out::println);

        System.out.println("---");
        streamSet.filter()
                .forEach(System.out::println);

        streamSet.list()
                .sorted()
                .forEach(System.out::println);

        streamSet.flatMap()
                .forEach(System.out::println);

        System.out.println("reduce");

        Stream.of(1, 2, 3)
                .reduce((a, b) -> a + b)
                .ifPresent(System.out::println);

        Stream.of(1, 2, 3)
                .reduce((a, b) -> a + b);

    }
}
