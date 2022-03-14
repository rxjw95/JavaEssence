package ch13.thread.excutioncontrol.sleep;

public class ControlPractice {
    public static void main(String[] args) {
        Thread sleepThread = new Thread(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {}
            System.out.println("sleep thread 3초 잠들기 완료");
        });

        sleepThread.start();

        /*try {
            sleepThread.sleep(3000); // sleepThread에 대한 sleep이 아닌 mainThread에서 sleep을 한다.
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        System.out.println("main thread 종료");
    }
}
