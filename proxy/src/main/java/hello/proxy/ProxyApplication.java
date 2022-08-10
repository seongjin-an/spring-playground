package hello.proxy;

import hello.proxy.config.AppV1Config;
import hello.proxy.config.AppV2Config;
import hello.proxy.config.LogTraceConfig;
import hello.proxy.config.v1_proxy.ConcreteProxyConfig;
import hello.proxy.config.v1_proxy.InterfaceProxyConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

//@Import(AppV1Config.class)//클래스를 스프링빈으로 등록한다.
//@Import({AppV1Config.class, AppV2Config.class})//클래스를 스프링빈으로 등록한다.
//@Import({LogTraceConfig.class, InterfaceProxyConfig.class})
@Import({ConcreteProxyConfig.class, LogTraceConfig.class})
@SpringBootApplication(scanBasePackages = "hello.proxy.app") //주의, scanBasePackages 는 현 모듈 패키지를 스캔
public class ProxyApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProxyApplication.class, args);
	}

}
/*
접근제어
 권한에 따른 접근 차단
 캐싱
 지연로딩
부가기능
 원래 서버가 제공하는 기능에 대해서 부가 기능을 수행한다.
 예) 요청 값이나, 응답 값을 중간에 변경한다.
 예) 실행 시간을 측정해서 추가 로그를 남긴다.
프록시체인

프록시패턴: 접근 제어가 목적
데코레이터 패턴: 새로운 기능 추가가 목적
 */