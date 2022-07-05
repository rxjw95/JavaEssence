package ch13.thread.lambdAndStream.lambda;

import java.security.SecureRandom;
import java.util.concurrent.Callable;
import java.util.function.*;
import java.util.function.Function;

class FunctionTester {
    public void functionAndPredicate() {
        String introduce = "i'm ground! introduce myself";

        Function<String, Integer> lengthConverter = str -> str.length();
        Predicate<Integer> greaterThan5 = val -> val > 5;

        if(greaterThan5.test(lengthConverter.apply(introduce))) {
            System.out.println("greater than 5");
        } else {
            System.out.println("less than 5");
        }
    }

    public void callable() throws Exception {
        Callable<Integer> randomGenerator = () -> SecureRandom.getInstanceStrong().nextInt(0, 10);
        Integer randomNumber = randomGenerator.call();
        System.out.println(randomNumber);
    }

    public void bi() {
        BiPredicate<Integer, Integer> greaterThan = (a, b) -> a > b;

        if(greaterThan.test(5, 6)) {
            System.out.println("nono");
        }
    }

    public void operator() {
        BinaryOperator<Integer> sum = (a, b) -> a + b;
        System.out.println(sum.apply(3, 5));
    }

    public void primitiveFunction() {
        DoubleToIntFunction doubleToIntFunc = a -> (int) Math.ceil(a);

        System.out.println(doubleToIntFunc.applyAsInt(5.2));
    }

    public void predicateOper() {
        String text = "183";
        Predicate<String> pattern = str -> str.matches("[0-9]*");
        Predicate<String> matcher = str -> str.length() < 5;
        Predicate<String> validator = pattern.and(matcher);

        if(validator.test(text)) {
            System.out.println("validated.");
        }
    }

    public void methodReference() {
        Function<String, Integer> lambda = s -> Integer.parseInt(s);
        Function<String, Integer> methodRef = Integer::parseInt;
    }
}
public class FunctionPractice {
    public static void main(String[] args) throws Exception {
        FunctionTester tester = new FunctionTester();

        tester.functionAndPredicate();
        tester.callable();
        tester.operator();
        tester.bi();
        tester.primitiveFunction();
        tester.predicateOper();
    }


}
