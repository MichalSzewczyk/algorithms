package com.algo.ciggarets;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Main {
    private static final Random RANDOM = new Random();
    private static final ReentrantLock[] LOCKS =
            new ReentrantLock[]{new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};
    private static final boolean[] INGREDIENTS = new boolean[3];
    private static final AtomicInteger PENDING_CIGARETTES = new AtomicInteger();

    public static void main(String[] args) {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(4);
        int paperIdx = 0;
        int tabacooIdx = 1;
        int matchIdx = 2;

        Runnable tabaccoOwner = () -> {
            getIngredient(matchIdx);
            System.out.println("Tabacco acquired match.");
            getIngredient(paperIdx);
            System.out.println("Tabacco acquired paper.");
            System.out.println("Tabacco owner is smoking.");
            PENDING_CIGARETTES.decrementAndGet();
        };

        Runnable paperOwner = () -> {
            getIngredient(matchIdx);
            System.out.println("Paper acquired match.");
            getIngredient(tabacooIdx);
            System.out.println("Paper acquired tabacco.");
            System.out.println("Paper owner is smoking.");
            PENDING_CIGARETTES.decrementAndGet();
        };

        Runnable matchOwner = () -> {
            getIngredient(paperIdx);
            System.out.println("Match acquired paper.");
            getIngredient(tabacooIdx);
            System.out.println("Match acquired tabacco.");
            System.out.println("Match owner is smoking.");
            PENDING_CIGARETTES.decrementAndGet();
        };

        Runnable agent = () -> {
            if (PENDING_CIGARETTES.get() == 0) {
                int[] randomIngredients = getTwoRandomIngredients();
                System.out.println(String.format("Agent is setting the ingredients. [ingredients=%s]",
                        Arrays.toString(randomIngredients)));
                PENDING_CIGARETTES.incrementAndGet();
                putIngredient(randomIngredients[0]);
                putIngredient(randomIngredients[1]);
            }
        };
        executor.scheduleAtFixedRate(tabaccoOwner, 1, 1000, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(paperOwner, 1, 1000, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(matchOwner, 1, 1000, TimeUnit.MILLISECONDS);
        executor.scheduleAtFixedRate(agent, 1, 1000, TimeUnit.MILLISECONDS);
    }

    private static void putIngredient(int idx) {
        LOCKS[idx].lock();
        INGREDIENTS[idx] = true;
        LOCKS[idx].unlock();
    }

    private static void getIngredient(int idx) {
        boolean ingredientAcquired = false;
        while (!ingredientAcquired) {
            LOCKS[idx].lock();
            if (INGREDIENTS[idx]) {
                INGREDIENTS[idx] = false;
                ingredientAcquired = true;
            }
            LOCKS[idx].unlock();
            if (!ingredientAcquired) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static int[] getTwoRandomIngredients() {
        return RANDOM.ints(0, 3).distinct().limit(2).toArray();
    }
}
