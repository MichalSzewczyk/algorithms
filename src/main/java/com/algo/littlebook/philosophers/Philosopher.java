package com.algo.littlebook.philosophers;

import java.util.concurrent.locks.Lock;

public class Philosopher {
    private final Lock leftFork;
    private final Lock rightFork;

    Philosopher(Lock leftFork, Lock rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    void dine(boolean leftFirst) {
        Lock left = leftFirst ? leftFork : rightFork;
        Lock right = leftFirst ? rightFork : leftFork;
        try {
            left.lock();
            System.out.println("First fork taken.");
            Thread.sleep(1000);
            right.lock();
            System.out.println("Second fork taken.");
            System.out.println("Dining");
            Thread.sleep(1000);
            left.unlock();
            System.out.println("First fork released.");
            Thread.sleep(1000);
            right.unlock();
            System.out.println("Second fork released.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
