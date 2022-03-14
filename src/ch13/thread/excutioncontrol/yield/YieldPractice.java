package ch13.thread.excutioncontrol.yield;

public class YieldPractice {
    public static void main(String[] args) {
        PrintThread printThread1 = new PrintThread("first");
        PrintThread printThread2 = new PrintThread("second");
        PrintThread printThread3 = new PrintThread("third");

        printThread1.start();
        printThread2.start();
        printThread3.start();

        try {
            Thread.sleep(2000);
            printThread1.suspend();
            System.out.println("-----------------------------");
            Thread.sleep(2000);
            printThread2.suspend();
            System.out.println("-----------------------------");

            Thread.sleep(2000);
            printThread1.resume();
            System.out.println("-----------------------------");

            Thread.sleep(2000);
            printThread1.stop();
            printThread2.stop();
            System.out.println("-----------------------------");

            Thread.sleep(2000);
            printThread3.stop();
            System.out.println("-----------------------------");

        } catch (InterruptedException e) {}

    }
}

class PrintThread implements Runnable {
    private boolean suspended = false;
    private boolean stopped = false;
    private final Thread thread;
    private final String text;

    public PrintThread(String text) {
        this.thread = new Thread(this);
        this.text = text;
    }

    @Override
    public void run() {
        while(!stopped) {
            if(!suspended) {
                System.out.println(text); // 종료되지도 않았고, 중지되지도 않은 상태라면 문자를 찍는다.
                try {
                    Thread.sleep(1000); // 후에 1초를 대기상태로 진입한다.
                } catch (InterruptedException e) {
                    System.out.println(text + " - interrupted."); // 대기중 인터럽트가 발생하면 interrupt 발생
                }
            } else {
                Thread.yield();  // 중지 상태에 있다면 다음 차례의 스레드에게 차례를 넘긴다.
            }
        }
        System.out.println(text + " - stopped.");
    }

    public void suspend() {
        suspended = true;
        thread.interrupt();
        System.out.println(text + " - interrupt by suspend.");
    }

    public void resume() {
        suspended = false;
    }

    public void stop() {
        stopped = true;
        thread.interrupt();
        System.out.println(text + " - interrupt by stop.");
    }

    public void start() {
        thread.start();
    }
}


