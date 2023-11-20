package com.algo.revolut.loadbalancer;

import java.net.URL;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

public class InMemoryLoadBalancer implements LoadBalancer {
    private final Function<Set<URL>, URL> electionStrategy;
    private final Object lock;
    private final Set<URL> urls;
    private final int maximumUrlCount;

    public InMemoryLoadBalancer(int maximumUrlCount, Function<Set<URL>, URL> electionStrategy) {
        this.electionStrategy = electionStrategy;
        this.maximumUrlCount = maximumUrlCount;
        urls = new HashSet<>();
        lock = new Object();
    }

    @Override
    public void register(URL url) throws LoadBalancerException {
        boolean isAdded;
        synchronized (lock) {
            if (urls.size() >= maximumUrlCount) {
                throw new URLCountExceededException();
            }
            isAdded = urls.add(url);
        }
        if (!isAdded) {
            throw new URLAlreadyRegisteredException();
        }
    }

    @Override
    public Optional<URL> elect() {
        if(urls.isEmpty()) {
            return Optional.empty();
        }
        URL selectedUrl = electionStrategy.apply(urls);
        return Optional.of(selectedUrl);
    }
}
