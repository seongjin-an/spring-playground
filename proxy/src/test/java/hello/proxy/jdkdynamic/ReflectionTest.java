package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class ReflectionTest {
    @Test
    void reflection0() {
        Hello target = new Hello();

        //공통 로직1 시작
        log.info("start");
        String result1 = target.callA();
        log.info("result={}", result1);
        //공통 로직1 종료

        //공통 로직2 시작
        log.info("start");
        String result2 = target.callB();
        log.info("result={}", result2);
        //공통 로직2 종료
    }

    @Slf4j
    static class Hello {
        public String callA() {
            log.info("callA");
            return "A";
        }

        public String callB() {
            log.info("callB");
            return "B";
        }
    }
}
/*
자바가 기본으로 제공하는 JDK 동적 프록시 기술이나 CGLIB 같은 프록시 생성 오픈소스 기술을 활용하면 프록시 객체를 동적으로 만들어 낼 수 있다.

JDK 동적 프록시를 이해하기 위해서는 먼저 자바의 리플렉션 기술을 이해해야 한다.
리플렉션 기술을 사용하면 클래스나 메서드의 메타정보를 동적으로 획득하고, 코드도 동적으로 호출할 수 있다.

공통로직1과 공통로직2는 호출하는 메서드만 다르고 전체 코드 흐림이 완전히 같다.
여기서 공통로직1과 공통로직2를 하나의 메서드로 뽑아서 합칠 수 있을까?
쉬워 보이지만, 메서드로 뽑아서 공토화하는 것이 생각보다 어렵다. 왜냐하면 중간에 호출하는 메서드가 다르기 때문이다.
호출하는 메서드인 target.callA(), target.callB() 이 부분만 동적으로 처리할 수 있다면 문제를 해결할 수 있다.

이럴 때 사용하는 기술이 바로 리플렉션이다.
 */
