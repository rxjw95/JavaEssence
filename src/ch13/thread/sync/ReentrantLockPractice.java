package ch13.thread.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

class Customer implements Runnable {
    private final Table table;
    private final String dish;

    Customer(Table table, String dish) {
        this.table = table;
        this.dish = dish;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
            }
            String threadName = Thread.currentThread().getName();
            table.remove(this.dish);
            System.out.printf("%s is ate a %s \n", threadName, this.dish);
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
            String[] distribution = table.getDistribution();
            int index = (int) (Math.random() * distribution.length);
            table.add(distribution[index]);
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
            }
        }
    }
}

class Table {
    private final String[] distribution = {"donut", "donut", "burger"};
    private final int MAX_DISHES_SIZE = 6;
    private List<String> dishes = new ArrayList<>();

    private ReentrantLock lock = new ReentrantLock();
    private Condition forCook = lock.newCondition();
    private Condition forCustomer = lock.newCondition();

    public void add(String dish) {
        lock.lock();

        try {
            while (dishes.size() >= MAX_DISHES_SIZE) {
                String threadName = Thread.currentThread().getName();
                System.out.printf("최대 개수 도달. %s is waiting.\n", threadName);

                try {
                    forCook.await();
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
            dishes.add(dish);
            forCustomer.signal();
            System.out.println("dishes: " + dishes);
        } finally {
            lock.unlock();
        }
    }

    public void remove(String dish) {
        lock.lock();
        String threadName = Thread.currentThread().getName();
        try {
            while (dishes.size() == 0) {
                System.out.printf("음식 소진. %s is waiting.\n", threadName);
                try {
                    forCustomer.await();
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }

            while(true) {
                for(int i = 0; i < dishes.size(); i ++) {
                    if(dish.equals(dishes.get(i))) {
                        dishes.remove(i);
                        forCook.signal();
                        return;
                    }
                }

                try {
                    System.out.printf("원하는 음식 없음. %s is waiting.\n", threadName);
                    forCustomer.await();
                    Thread.sleep(500);
                } catch (InterruptedException e) {}
            }
        } finally {
            lock.unlock();
        }
    }

    public String[] getDistribution() {
        return distribution;
    }
}

public class ReentrantLockPractice {
    public static void main(String[] args) throws InterruptedException {
        Table table = new Table();

        new Thread(new Cook(table), "COOK").start();
        new Thread(new Customer(table, "burger"), "CUSTOMER1").start();
        new Thread(new Customer(table, "donut"), "CUSTOMER2").start();
        new Thread(new Customer(table, "donut"), "CUSTOMER3").start();
        new Thread(new Customer(table, "donut"), "CUSTOMER4").start();
        new Thread(new Customer(table, "burger"), "CUSTOMER5").start();

        Thread.sleep(2000);
        System.exit(0);
    }
}
