package com.carrot.rabbit

import com.carrot.rabbit.dto.User
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class ObjectMapperTest {
    @Test
    fun objectMapperTest() {
        val objectMapper = ObjectMapper()
        // object -> text, getter!!!
        val user: User = User("ansj", 20)
        var text = objectMapper.writeValueAsString(user)
        println("text: $text")

        // text -> object, constructor!!!
        val objectUser: User = objectMapper.readValue(text, User::class.java)
        println("objectUser: $objectUser")
    }
}