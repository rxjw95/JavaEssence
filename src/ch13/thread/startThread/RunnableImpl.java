package ch13.thread.startThread;

import java.util.stream.IntStream;

public class RunnableImpl implements Runnable{
    @Override
    public void run() {
        IntStream.range(0, 5).forEach(idx -> System.out.println("runnableImpl:" + idx));
    }
}
