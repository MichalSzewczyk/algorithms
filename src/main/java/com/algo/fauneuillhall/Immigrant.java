package com.algo.fauneuillhall;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Immigrant implements Runnable {
    private final ReentrantLock judgeInBuilding;
    private final ReentrantLock immigrantsLock;
    private final AtomicInteger immigrantsNotCheckedIn;
    private final int id;
    private static final AtomicInteger MAX_ID = new AtomicInteger();

    public Immigrant(ReentrantLock judgeInBuilding, ReentrantLock immigrantsLock, AtomicInteger immigrantsNotCheckedIn) {
        this.judgeInBuilding = judgeInBuilding;
        this.immigrantsLock = immigrantsLock;
        this.immigrantsNotCheckedIn = immigrantsNotCheckedIn;
        this.id = MAX_ID.incrementAndGet();
    }

    @Override
    public void run() {
        try {
            enter();
            checkIn();
            sitDown();
            getCertificate();
            leave();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void enter() {
        judgeInBuilding.lock();
        immigrantsLock.lock();
        System.out.println(String.format("Immigrant entered. [id=%s]", id));
        immigrantsNotCheckedIn.incrementAndGet();
        immigrantsLock.unlock();
        judgeInBuilding.unlock();

    }

    void checkIn() {
        immigrantsLock.lock();
        if (immigrantsNotCheckedIn.decrementAndGet() == 0) {
            synchronized (immigrantsNotCheckedIn) {
                immigrantsNotCheckedIn.notifyAll();
            }
        }
        immigrantsLock.unlock();
        System.out.println(String.format("Immigrant checked in. [id=%s]", id));
    }

    void sitDown() {
        System.out.println(String.format("Immigrant sat. [id=%s]", id));
    }

    void getCertificate() {
        synchronized (immigrantsLock) {
            try {
                immigrantsLock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(String.format("Immigrant got certificate. [id=%s]", id));
    }

    void leave() {
        judgeInBuilding.lock();
        System.out.println(String.format("Immigrant leaved. [id=%s]", id));
        judgeInBuilding.unlock();
    }

}
