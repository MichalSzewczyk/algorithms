package com.algo.race;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ThreadLocalMain {
    public static void main(String[] args) {
        Executor first = Executors.newSingleThreadExecutor();
        Executor second = Executors.newSingleThreadExecutor();
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        first.execute(new InitializationTask(threadLocal, "first"));
        second.execute(new InitializationTask(threadLocal, "second"));
        first.execute(new PrintingTask(threadLocal));
        second.execute(new PrintingTask(threadLocal));

    }

    static class InitializationTask implements Runnable {
        private final ThreadLocal<String> threadLocal;
        private final String value;

        public InitializationTask(ThreadLocal<String> threadLocal, String value) {
            this.threadLocal = threadLocal;
            this.value = value;
        }

        @Override
        public void run() {
            threadLocal.set(value);
        }
    }

    static class PrintingTask implements Runnable {
        private final ThreadLocal<String> threadLocal;

        PrintingTask(ThreadLocal<String> threadLocal) {
            this.threadLocal = threadLocal;
        }

        @Override
        public void run() {
            System.out.println(threadLocal.get());
        }
    }
}
