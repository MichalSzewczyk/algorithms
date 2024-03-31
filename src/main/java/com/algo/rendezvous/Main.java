package com.algo.rendezvous;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(2);
        Semaphore semaphoreFirst = new Semaphore(0);
        Semaphore semaphoreSecond = new Semaphore(0);
        service.submit(() -> Main.firstAction(semaphoreFirst, semaphoreSecond));
        service.submit(() -> Main.secondAction(semaphoreFirst, semaphoreSecond));
    }

    private static boolean firstAction(Semaphore semaphoreFirst, Semaphore semaphoreSecond) throws InterruptedException {
        System.out.println("Statement first 1");
        semaphoreFirst.release();
        semaphoreSecond.acquire();
        System.out.println("Statement first 2");
        return true;
    }

    private static boolean secondAction(Semaphore semaphoreFirst, Semaphore semaphoreSecond) throws InterruptedException {
        System.out.println("Statement second 1");
        semaphoreSecond.release();
        semaphoreFirst.acquire();
        System.out.println("Statement second 2");
        return true;
    }
}
