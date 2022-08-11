package hello.proxy.jdkdynamic;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

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

    @Test
    void reflection1() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");//내부 클래스는 $ 를 추가한다. // 메타정보 획득

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
        Object result1 = methodCallA.invoke(target);//호출
        log.info("result1={}", result1);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
        Object result2 = methodCallB.invoke(target);//호출
        log.info("result2={}", result2);
        /*
        그런데 target.callA() 나 target.callB() 메서드를 직접 호출하면 되지 이렇게 메서드 정보를 획득해서 메서드 호출하면 어떤 효과가 있을까?
        여기서 중요한 핵심은 클래스나 메서드 정보를 동적으로 변경할 수 있다는 점이다.

        기존의 callA(), callB() 메서드를 직접 호출하는 부분이 Method로 대체되었다. 덕분에 이제 공통 로직을 만들 수 있게 된다.
         */
    }

    @Test
    void reflection2() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        //클래스 정보
        Class classHello = Class.forName("hello.proxy.jdkdynamic.ReflectionTest$Hello");//내부 클래스는 $ 를 추가한다. // 메타정보 획득

        Hello target = new Hello();
        //callA 메서드 정보
        Method methodCallA = classHello.getMethod("callA");
//        Object result1 = methodCallA.invoke(target);//호출
//        log.info("result1={}", result1);
        dynamicCall(methodCallA, target);

        //callB 메서드 정보
        Method methodCallB = classHello.getMethod("callB");
//        Object result2 = methodCallB.invoke(target);//호출
//        log.info("result2={}", result2);
        dynamicCall(methodCallB, target);
        /*
        dynamicCall(Method method, Object target)
        공통 로직1, 공통 로직2를 한번에 처리할 수 있는 공통처리 로직이다.
        Method method: 첫번째 파라미터는 호출할 메서드 정보가 넘어온것이다. 이것이 핵심이다. 기존에는 메서드 이름을 직접 호출했지만,
        이제는 Method라는 메타정보를 통해서 호출할 메서드 정보가 동적으로 제공된다.
        Object target: 실제 실행할 인스턴스 정보가 넘어온다. 타입이 Object라는 것은 어떤한 인스턴스도 받을 수 있다는 뜻이다.
        물론 method.invoke(target) 를 사용할 때호출할 클래스와 메서드 정보가 서로 다르면 예외가 발생한다.

        **정리**
        정적인 target.callA(), target.callB() 코드를 리플렉션을 사용해서 Method라는 메타정보로 추상화했다. 덕분에 공통로직을 만들 수 있게 된다.

        **주의**
        리플렉션을 사용하면 클래스와 메서드의 메타정보를 사용해서 애플리케이션을 동적으로 유연하게 만들 수 있다. 하지만 리플렉션 기술은 런타임에
        동작하기 때문에,컴파일 시점에 오류를 잡을 수 없다.

         */
    }

    private void dynamicCall(Method method, Object target) throws InvocationTargetException, IllegalAccessException {
        log.info("start");
//        String result1 = target.callA();
        Object result = method.invoke(target);
        log.info("result={}", result);
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

참고로 람다를 사용해서 공통화하는 것도 가능하다.
 */
