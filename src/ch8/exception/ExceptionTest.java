package ch8.exception;

public class ExceptionTest {
    public static void main(String[] args) {
        /*int a = 3;
        int b = 0;
        try {
            a = 12 / 1;
            b = 11 / 1;
            throw new ArithmeticException();
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.out.println("예외 발생");
        }
        System.out.println(a);*/
        method1();
    }

    static void method1() {
        try {
            method2();
        } catch (Exception e) {
            System.out.println("method1에서 예외를 처리합니다.");
        }
    }

    // 예외 회피
    static void method2() throws Exception {
        throw new Exception();
    }
}
