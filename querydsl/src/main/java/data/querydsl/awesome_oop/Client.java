package data.querydsl.awesome_oop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Client {
    private final Developer<Programming> developer;

    public void client() {
        Programming programming = new ProgrammingImpl();
        String result = developer.getResult(programming);
        System.out.println("client build: " + result);
    }
}
