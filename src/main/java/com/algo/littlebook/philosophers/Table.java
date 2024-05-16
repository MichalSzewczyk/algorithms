package com.algo.littlebook.philosophers;

import java.util.Collection;
import java.util.LinkedList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Table {
    private final Collection<Philosopher> philosophers;
    private final ScheduledExecutorService executor;

    public Table(int philosopherCount) {
        if(philosopherCount < 1) {
            throw new IllegalArgumentException();
        }
        this.philosophers = new LinkedList<>();
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
        executor = Executors.newScheduledThreadPool(philosopherCount);
    }

    void dine() {
        for(Philosopher philosopher: philosophers) {
            executor.schedule(philosopher::dine, 1, TimeUnit.SECONDS);
        }
    }
}
