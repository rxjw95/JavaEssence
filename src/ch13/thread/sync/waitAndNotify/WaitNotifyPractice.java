package ch13.thread.sync.waitAndNotify;

import java.util.ArrayList;

public class WaitNotifyPractice {
    public static void main(String[] args) throws InterruptedException {
        Table table = new Table();

        new Thread(new Cook(table), "COOK1").start();
        new Thread(new Customer(table, "donut"), "CUST1").start();
        new Thread(new Customer(table, "burger"), "CUST2").start();
        new Thread(new Customer(table, "burger"), "CUST3").start();
        new Thread(new Customer(table, "burger"), "CUST4").start();
        new Thread(new Customer(table, "donut"), "CUST5").start();
        new Thread(new Customer(table, "donut"), "CUST6").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}

class Customer implements Runnable {
    private final Table table;
    private final String food;

    Customer(Table table, String food) {
        this.table = table;
        this.food = food;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            String threadName = Thread.currentThread().getName();

            table.remove(food);
            System.out.println(threadName + " ate a " + food);
        }
    }
}

class Cook implements Runnable {
    private final Table table;

    Cook(Table table) {
        this.table = table;
    }

    @Override
    public void run() {
        while (true) {
            String[] dishDistribution = table.getDishDistribution();
            int index = (int) (Math.random() * dishDistribution.length);
            table.add(dishDistribution[index]);

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                ;
            }
        }
    }
}

class Table {
    private final String[] dishDistribution = {"donut", "donut", "burger"}; // 음식이 나올 확률을 donut이 더 높도록 한다.
    private final int MAX_FOOD = 6;
    private final ArrayList<String> dishes = new ArrayList<>();

    public synchronized void add(String dish) {
        if (dishes.size() >= MAX_FOOD) {
            return;
        }
        dishes.add(dish);
        System.out.println("현재 나온 음식: " + dishes);
    }

    public void remove(String dishName) {
        synchronized (this) {
            String threadName = Thread.currentThread().getName();
            while (dishes.size() == 0) {
                System.out.println("음식 소진. " + threadName + " is waiting");
                try {
                    wait();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    ;
                }
            }

            while (true) {
                for (int i = 0; i < dishes.size(); i++) {
                    if (dishName.equals(dishes.get(i))) {
                        dishes.remove(i);
                        notify();
                        return;
                    }
                }

                try {
                    System.out.println("원하는 음식이 없음." + threadName + " is waiting.");
                    wait();
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    ;
                }
            }
        }

    }

    public String[] getDishDistribution() {
        return this.dishDistribution;
    }

}
