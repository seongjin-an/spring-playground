package data.querydsl.awesome_oop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor
public class Client {
    private final Person<Programming> developer;
    public Client(Person<Programming> developer){
        this.developer = developer;
    }

    public void client() {
        Programming programming = new ProgrammingImpl();
        String result = developer.getResult(programming);
        System.out.println("client build: " + result);
    }
}
