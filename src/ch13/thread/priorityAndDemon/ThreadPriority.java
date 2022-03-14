package ch13.thread.priorityAndDemon;

import java.util.stream.IntStream;

public class ThreadPriority {
    public static void main(String[] args) {
        SubThread_1 subThread_1 = new SubThread_1();
        SubThread_2 subThread_2 = new SubThread_2();
        subThread_1.setPriority(10); // (최대 우선 순위값) 가장 우선순위가 높다.
        subThread_2.setPriority(1); // (최소 우선 순위값) 가장 우선순위가 낮다.

        subThread_1.start();
        subThread_2.start();
    }
}

class SubThread_1 extends Thread {
    @Override
    public void run() {
        IntStream.range(0, 1000).forEach(any -> System.out.print("-"));
    }
}

class SubThread_2 extends Thread {
    @Override
    public void run() {
        IntStream.range(0, 1000).forEach(any -> System.out.print("|"));
    }
}