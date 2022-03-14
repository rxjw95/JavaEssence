package ch13.thread.priorityAndDemon;


public class DaemonThread {
    static boolean auto = false;

    public static void main(String[] args) {
        Daemon daemon = new Daemon();
        daemon.setDaemon(true);
        daemon.start();

        for(int i = 0 ; i < 10; i ++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            System.out.println(i);
            if(i == 5) {
                auto = true;
            }
        }

        System.out.println("프로그램 종료");
    }
}

class Daemon extends Thread {
    @Override
    public void run() {
        while(true) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println("데몬 스레드 종료");
            }
            if(DaemonThread.auto) {
                System.out.println("자동 저장되었습니다.");
            }
        }
    }
}

