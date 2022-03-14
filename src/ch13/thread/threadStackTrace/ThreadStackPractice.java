package ch13.thread.threadStackTrace;

public class ThreadStackPractice {
    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.start(); // 호출 스택의 첫 번째 메서드가 main이 아닌 run이다.
        subThread.run(); // 호출 스택의 첫 번째 메서드가 main이다.
    }
}

class SubThread extends Thread {
    @Override
    public void run() {
        throwException();
    }

    public void throwException() {
        try {
            throw new Exception();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

}