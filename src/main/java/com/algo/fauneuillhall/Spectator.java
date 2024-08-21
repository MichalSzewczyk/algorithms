package com.algo.fauneuillhall;

import java.util.concurrent.locks.ReentrantLock;

public class Spectator implements Runnable {
    private final ReentrantLock judgeInBuilding;

    public Spectator(ReentrantLock judgeInBuilding) {
        this.judgeInBuilding = judgeInBuilding;
    }

    @Override
    public void run() {
        enter();
        spectate();
        leave();
    }

    void enter() {
        judgeInBuilding.lock();
        System.out.println("Spectator entered.");
        judgeInBuilding.unlock();
    }

    void spectate() {

    }

    void leave() {
        System.out.println("Spectator left.");
    }
}
