package data.querydsl.awesome_oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {
    @Bean
    public Person<Programming> developer(){
        Person<Programming> programmingDeveloper = new Developer<>();
        return programmingDeveloper;
    }
}
