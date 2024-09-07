package com.algo.rwlock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeMap implements SimplifiedMap<String, String> {
    private final Map<String, String> map;
    private final ReentrantLock readerLock;
    private int readers;
    private boolean writerWaiting;

    public ThreadSafeMap() {
        map = new HashMap<>();
        readerLock = new ReentrantLock();
    }

    @Override
    public void put(String key, String value) {
        while (true) {
            readerLock.lock();
            if (readers == 0) {
                readers = -1;
                readerLock.unlock();
                break;
            } else {
                readerLock.unlock();
                try {
                    readerLock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted");
                }
            }
        }
        map.put(key, value);
        readers = 0;
        readerLock.notifyAll();
    }

    @Override
    public String get(String key) {
        readerLock.lock();
        if (readers == -1) {
            while (readers == -1) {
                try {
                    readerLock.wait();
                } catch (InterruptedException e) {
                    System.out.println("Interrupted.");
                }
            }
        }
        readers++;
        readerLock.unlock();

        String result = map.get(key);

        readerLock.lock();
        readers--;
        readerLock.unlock();
        readerLock.notifyAll();
        return result;
    }
}
