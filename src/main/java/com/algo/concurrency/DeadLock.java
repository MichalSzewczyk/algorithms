package com.algo.concurrency;

public class DeadLock {
    public static void main(String[] args) {
        InfiniteTask task = new InfiniteTask();
        Thread first = new Thread(task);
        first.start();
        Thread second = new Thread(() -> {
            synchronized ("FOO") {
                task.setFinish(true);
            }
        });
        second.start();
    }


    static class InfiniteTask implements Runnable {
        private volatile boolean finish;

        @Override
        public void run() {
            synchronized("FOO") {
                while(!finish) {
                    System.out.println("Running.");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public void setFinish(boolean finish) {
            this.finish = finish;
        }
    }
}
