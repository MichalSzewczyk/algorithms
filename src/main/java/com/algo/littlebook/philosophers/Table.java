package com.algo.littlebook.philosophers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    private final List<Philosopher> philosophers;
    private final ScheduledExecutorService executor;

    public Table(int philosopherCount) {
        if (philosopherCount < 1) {
            throw new IllegalArgumentException();
        }
        this.philosophers = initializePhilosophers(philosopherCount);
        executor = Executors.newScheduledThreadPool(philosopherCount);
    }

    void dineLeftFirst() {
        for (Philosopher philosopher : philosophers) {
            executor.scheduleAtFixedRate(() -> philosopher.dine(true), 1, 1, TimeUnit.SECONDS);
        }
    }

    void dine() {
        for (int idx = 0; idx < philosophers.size() - 1; idx++) {
            final int index = idx;
            executor.scheduleAtFixedRate(() -> philosophers.get(index).dine(true), 1, 1, TimeUnit.SECONDS);
        }
        executor.schedule(() -> philosophers.get(philosophers.size() - 1).dine(false), 1, TimeUnit.SECONDS);
    }

    private static List<Philosopher> initializePhilosophers(int philosopherCount) {
        List<Philosopher> philosophers = new LinkedList<>();
        Lock firstLock = new ReentrantLock();
        Lock previousLock = firstLock;
        for (int idx = 0; idx < philosopherCount - 1; idx++) {
            Lock currentLock = new ReentrantLock();
            Philosopher philosopher = new Philosopher(previousLock, currentLock);
            philosophers.add(philosopher);
            previousLock = currentLock;
        }
        Philosopher philosopher = new Philosopher(previousLock, firstLock);
        philosophers.add(philosopher);
        return philosophers;
    }
}
