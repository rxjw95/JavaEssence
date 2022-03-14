package ch13.thread.startThread;

import java.util.stream.IntStream;

/*
SubThread.java
 */
public class SubThread extends Thread {
    @Override
    public void run() {
        IntStream.range(0, 5).forEach(idx -> System.out.println("subThread: " + idx));
    }
}
