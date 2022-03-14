package ch13.thread.excutioncontrol.interrupt;

import java.util.Scanner;

public class InterruptPractice {
    public static void main(String[] args) throws InterruptedException {
        Scanner sc = new Scanner(System.in);
        Thread task = new Thread(() -> {
            int i = 10;
            while(i != 0 && !Thread.interrupted()) {
                System.out.println(i--);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(i == 0 ? "count end" : "interrupted");
        });

        task.start();

        String input = sc.next();
        System.out.println(input);
        task.interrupt();
        sc.close();
    }
}


