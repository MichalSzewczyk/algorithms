package com.algo.fauneuillhall;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        ReentrantLock judgeInBuilding = new ReentrantLock();
        ReentrantLock immigrantsLock = new ReentrantLock();
        AtomicInteger immigrantsNotCheckedIn = new AtomicInteger();
        service.submit(new Immigrant(judgeInBuilding, immigrantsLock, immigrantsNotCheckedIn));
        service.submit(new Immigrant(judgeInBuilding, immigrantsLock, immigrantsNotCheckedIn));
        service.submit(new Immigrant(judgeInBuilding, immigrantsLock, immigrantsNotCheckedIn));
        service.submit(new Judge(judgeInBuilding, immigrantsLock, immigrantsNotCheckedIn));
        service.submit(new Spectator(judgeInBuilding));
    }

}
