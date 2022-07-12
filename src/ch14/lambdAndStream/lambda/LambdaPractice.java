package ch14.lambdAndStream.lambda;

@FunctionalInterface
interface Function {
    int operate(int a, int b);
}

class Calculator {
    public static int calculate(int a, int b, Function operatorFunc) {
        return operatorFunc.operate(a, b);
    }
}

@FunctionalInterface
interface Printer {
    void print();
}

class Outer {
    int outerVal = 1;

    class Inner {
        private int innerVal = 2;

        public void print(int p) {
            int i = 3;
            Printer printer = () -> {
                System.out.println("outerVal: " + outerVal);
                System.out.println("innerVal: " + innerVal);
                System.out.println("innerVal one increase: " + ++innerVal);
                System.out.println("outerVal one decrease: " + --outerVal);
                //System.out.println("i: " + ++i);
                //System.out.println("i: " + ++p);
            };

            printer.print();
        }
    }
}

public class LambdaPractice {
    public static void main(String[] args) {
        Function add = (a, b) -> a + b;
        Function sub = (a, b) -> a - b;

        Calculator.calculate(3, 5, add);
        Calculator.calculate(5, 3, (a, b) -> a - b); // sub Function과 동일

        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner();

        inner.print(3);
        System.out.println(outer.outerVal);
    }
}
