package hello.proxy.pureProxy.concreteproxy.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConcreteLogic {
    public String operation() {
        log.info("Concrete logic 실행");
        return "data";
    }
}
