package com.ansj.ioc.components

import com.ansj.ioc.util.Encoder
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class PersonEncoderTest {
    @Autowired
    private lateinit var personEncoder: PersonEncoder

    @Test
    fun base64EncoderTest(){
        val result = personEncoder.encode()
        println("base64encoder result: $result");
        assertNotNull(result)
    }
}