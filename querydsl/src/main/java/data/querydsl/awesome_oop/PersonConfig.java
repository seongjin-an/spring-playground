package data.querydsl.awesome_oop;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {
    @Bean
    public Developer<Programming> developer(){
        Developer<Programming> programmingDeveloper = new Developer<>();
        return programmingDeveloper;
    }
}
