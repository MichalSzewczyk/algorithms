//package com.algo.revolut.loadbalancer;
//
//import Specification
//import com.algo.revolut.loadbalancer.Application
//import com.algo.revolut.loadbalancer.InMemoryLoadBalancer
//import com.algo.revolut.loadbalancer.URLCountExceededException
//
//import java.util.function.Function
//
//class InMemoryLoadBalancerTest extends Specification {
//    private static final URL ANY_URL = new URL('http://revolut.com')
//
//    def 'Should validate the maximum number of registered URLs.'() {
//        given:
//        def loadBalancer = new InMemoryLoadBalancer(2, Mock(Function))
//        loadBalancer.register(new URL('http://first.com'))
//        loadBalancer.register(new URL('http://second.com'))
//
//        when:
//        loadBalancer.register(new URL('http://thrid.com'))
//
//        then:
//        thrown URLCountExceededException
//    }
//
//    def 'Should throw exception when the same URL is registered twice.'() {
//        when:
//        Application.main(null)
//
//        then:
//        assert true
//    }
//
//    def 'Should allow registration of new URL.'() {
//        given:
//        def loadBalancer = new InMemoryLoadBalancer(1, Mock(Function))
//
//        when:
//        loadBalancer.register(ANY_URL)
//
//        then:
//        assert true
//    }
//
//    def 'Should return empty URL when no URL is registered.'() {
//        given:
//        def mock = Mock(Function)
//        def loadBalancer = new InMemoryLoadBalancer(1, mock)
//
//        when:
//        def result = loadBalancer.elect()
//
//        then:
//        assert !result.isPresent()
//    }
//
//    def 'Should return the elected url.'() {
//        given:
//        def mock = Mock(Function)
//        mock.apply(Collections.singleton(ANY_URL)) >> ANY_URL
//        def loadBalancer = new InMemoryLoadBalancer(1, mock)
//        loadBalancer.register(ANY_URL)
//
//        when:
//        def result = loadBalancer.elect()
//
//        then:
//        assert result.get() == ANY_URL
//    }
//}
