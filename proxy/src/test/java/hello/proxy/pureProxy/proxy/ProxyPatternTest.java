package hello.proxy.pureProxy.proxy;

import hello.proxy.pureProxy.proxy.code.CacheProxy;
import hello.proxy.pureProxy.proxy.code.ProxyPatternClient;
import hello.proxy.pureProxy.proxy.code.RealSubject;
import org.junit.jupiter.api.Test;

public class ProxyPatternTest {
    @Test
    void noProxyTest(){
        RealSubject realSubject = new RealSubject();
        ProxyPatternClient client = new ProxyPatternClient(realSubject);
        client.execute();
        client.execute();
        client.execute();
    }

    @Test
    void cacheProxyTest(){
        RealSubject realSubject = new RealSubject();
        CacheProxy cacheProxy = new CacheProxy(realSubject);
        ProxyPatternClient proxyPatternClient = new ProxyPatternClient(cacheProxy);

        proxyPatternClient.execute();
        proxyPatternClient.execute();
        proxyPatternClient.execute();
    }
}
