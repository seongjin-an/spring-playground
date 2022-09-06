package jpabook.jpashop2;

import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Jpashop2Application {

    public static void main(String[] args) {
        SpringApplication.run(Jpashop2Application.class, args);
    }

//    @Bean
    public Hibernate5Module hibernate5Module() {
//        return new Hibernate5Module();
        // 이 작업은 엔티티를 고의적으로 외부로 노출시켜야 해서 하는 것,
        // 잘 설계한 곳에서는 필요도 없는 작업이다.
        Hibernate5Module hibernate5Module = new Hibernate5Module();
        hibernate5Module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, true);
        return hibernate5Module;
    }

}
