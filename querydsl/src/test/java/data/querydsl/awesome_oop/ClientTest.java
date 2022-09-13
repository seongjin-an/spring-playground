package data.querydsl.awesome_oop;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ClientTest {
    @Autowired
    Client client;

    @DisplayName("client test")
    @Test
    public void clientTest() throws Exception{
        client.client();
    }
}