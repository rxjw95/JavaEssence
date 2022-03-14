package ch13.thread.excutioncontrol.join;

public class JoinPractice {
    public static void main(String[] args) {
        Thread job1 = new Thread(() -> {
            try{
                for(int i = 0; i < 100; i++) {
                    System.out.print("-");
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {}
        });

        job1.start();

        try {
            job1.join();
        } catch (InterruptedException e) {}

        for(int i = 0; i < 100; i ++) {
            System.out.print("*");
        }
    }
}


