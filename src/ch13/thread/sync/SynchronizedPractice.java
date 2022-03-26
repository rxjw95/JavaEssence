package ch13.thread.sync;

public class SynchronizedPractice {
    public static void main(String[] args) {
        Runnable accountRunnable = new AccountRunnable();
        new Thread(accountRunnable).start();
        new Thread(accountRunnable).start();
    }
}

class Account {
    private int balance = 1000;

    /*public synchronized void withDraw(int money) {
        if(balance >= money) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            balance -= money;
        }
    }*/

    public void withDraw(int money) {
        synchronized (this) {
            if(balance >= money) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                balance -= money;
            }
        }
    }

    public int getBalance() {
        return balance;
    }
}

class AccountRunnable implements Runnable {
    private final Account account = new Account();

    @Override
    public void run() {
        while(account.getBalance() > 0) {
            int money = (int) (Math.random() * 3 + 1) * 100;
            account.withDraw(money);
            System.out.println(account.getBalance());
        }
    }
}
