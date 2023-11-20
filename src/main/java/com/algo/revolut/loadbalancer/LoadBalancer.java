package com.algo.revolut.loadbalancer;

import java.net.URL;
import java.util.Optional;

public interface LoadBalancer {
    void register(URL url) throws LoadBalancerException;

    Optional<URL> elect();
}
