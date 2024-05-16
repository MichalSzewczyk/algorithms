package com.algo.littlebook.philosophers;

import java.util.concurrent.locks.Lock;

public class Philosopher {
    private final Lock leftFork;
    private final Lock rightFork;

    Philosopher(Lock leftFork, Lock rightFork) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
    }

    void dine() {
        try {
            leftFork.lock();
            System.out.println("Left fork taken.");
            Thread.sleep(1000);
            rightFork.lock();
            System.out.println("Right fork taken.");
            System.out.println("Dining");
            Thread.sleep(1000);
            leftFork.unlock();
            System.out.println("Left fork released.");
            Thread.sleep(1000);
            rightFork.unlock();
            System.out.println("Right fork released.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
