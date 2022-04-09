package com.ansj.ioc.components

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class DeveloperEncoderTest {
    @Autowired
    private lateinit var developerEncoder: DeveloperEncoder

    @Test
    fun urlEncoderTest(){
        val result = developerEncoder.encode()
        println("url encoder result: $result")
        assertNotNull(result)
    }
}