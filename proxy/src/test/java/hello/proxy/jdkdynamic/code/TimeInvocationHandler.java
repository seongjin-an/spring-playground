package hello.proxy.jdkdynamic.code;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

@Slf4j
public class TimeInvocationHandler implements InvocationHandler {

    private final Object target;

    public TimeInvocationHandler(Object target) {
        this.target = target;
    }

    /*
    TimeInvocationHandler 는 InvocationHandler 인터페이스를 구현한다. 이렇게해서 JDK 동적 프록시에 적용할 공통 로직을 개발할 수 있다.
    Object target 동적 프록시가 호출할 대상
    method.invoke(target, args): 리플렉션을 사용해서 target 인스턴스의 메서드를 실행한다. args 는 메서드 호출 시 넘겨줄 인수이다.
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        log.info("TimeProxy 실행");

        long startTime = System.currentTimeMillis();

        Object result = method.invoke(target, args); // call

        long endTime = System.currentTimeMillis();

        long resultTime = endTime - startTime;

        log.info("TimeProxy 종료 resultTime={}", resultTime);

        return result;
    }
    /*
    생성된 JDK 동적 프록시
    proxyClass=class com.sun.proxy.$Proxy1 이 부분이 동적으로 생성된 프록시 클래스 정보이다. 이것은 만든 클래스가 아니라 JDK 동적 프록시가
    이름 그대로 동적으로 만들어준 프록시이다. 이 프록시는 TimeInvocationHandler 로직을 실행한다.

    실행순서
    1.클라이언트는 JDK 동적 프록시의 call()을 실행한다.
    2.JDK 동적 프록시는 InvocationHandler.invoke() 를 호출한다. TimeInvocationHandler 가 구현체로 있으므로
    TimeInvocationHandler.invoke()가 호출된다.
    3.TimeInvocationHandler 가 내부 로직을 수행하고, method.invoke(target, args) 를 호출해서 target 인 실제 객체(AImpl)를 호출한다.
    4.AImpl 인스턴스의 call() 이 실행된다.
    5.AImpl 인스턴스의 call() 의 실행이 끝나면 TimeInvocationHandler 로 응답이 돌아온다. 시간 로그를 출력하고 결과를 반환한다.
     */
}
