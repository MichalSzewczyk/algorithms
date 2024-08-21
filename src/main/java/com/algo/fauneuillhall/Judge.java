package com.algo.fauneuillhall;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Judge implements Runnable {
    private final ReentrantLock inBuilding;
    private final ReentrantLock immigrantsLock;
    private final AtomicInteger immigrantsNotCheckedIn;

    public Judge(ReentrantLock inBuilding, ReentrantLock immigrantsLock, AtomicInteger immigrantsNotCheckedIn) {
        this.inBuilding = inBuilding;
        this.immigrantsLock = immigrantsLock;
        this.immigrantsNotCheckedIn = immigrantsNotCheckedIn;
    }

    @Override
    public void run() {
        enter();
        confirm();
        leave();
    }

    void enter() {
        inBuilding.lock();
        System.out.println("Judge entered.");
    }

    void confirm() {
        boolean confirmed = false;
        immigrantsLock.lock();
        if (immigrantsNotCheckedIn.get() == 0) {
            System.out.println("Confirmed all immigrants");
            confirmed = true;
        }
        immigrantsLock.unlock();
        while (!confirmed) {
            try {
                synchronized (immigrantsNotCheckedIn) {
                    immigrantsNotCheckedIn.wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            immigrantsLock.lock();
            if (immigrantsNotCheckedIn.get() == 0) {
                System.out.println("Confirmed all immigrants");
                confirmed = true;
            }
            immigrantsLock.unlock();
        }
        synchronized (immigrantsLock) {
            immigrantsLock.notifyAll();
        }
    }

    void leave() {
        System.out.println("Judge left.");
        inBuilding.unlock();
    }

}
