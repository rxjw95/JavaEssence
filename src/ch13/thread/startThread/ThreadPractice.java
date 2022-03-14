package ch13.thread.startThread;

import java.util.stream.IntStream;

public class ThreadPractice {
    public static void main(String[] args) {
        SubThread subThread = new SubThread();
        subThread.start();

        Runnable runnable = new RunnableImpl();
        Thread runnableImpl = new Thread(runnable);
        runnableImpl.start();

        // 아래는 runnable 인터페이스 구현체를 직접 만들지 않고, 익명 클래스 그리고 람다식으로 표현하는 방법이다.
        Thread runnableAnonymous = new Thread(new Runnable() {
            @Override
            public void run() {
                IntStream.range(0, 5).forEach(idx -> System.out.println("anonymous: " + idx));
            }
        });

        Thread runnableLambda = new Thread(() -> {
            IntStream.range(0, 5).forEach(idx -> System.out.println("lambda: " + idx));
        });
    }
}
