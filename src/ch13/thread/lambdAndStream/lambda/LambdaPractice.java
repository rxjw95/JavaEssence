package ch13.thread.lambdAndStream.lambda;

@FunctionalInterface
interface Function {
    int operate(int a, int b);
}

class Calculator {
    public static int calculate(int a, int b, Function operatorFunc) {
        return operatorFunc.operate(a, b);
    }
}

public class LambdaPractice {
    public static void main(String[] args) {
        Function add = (a, b) -> a + b;
        Function sub = (a, b) -> a - b;

        Calculator.calculate(3, 5, add);
        Calculator.calculate(5, 3, (a, b) -> a - b); // sub Function과 동일
    }
}
