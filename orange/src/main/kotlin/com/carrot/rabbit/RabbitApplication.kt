package com.carrot.rabbit

import com.carrot.rabbit.config.ApplicationContextProvider
import com.carrot.rabbit.util.encode.Base64Encoder
import com.carrot.rabbit.util.encode.Encoder
import com.carrot.rabbit.util.encode.UrlEncoder
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*

@SpringBootApplication
class RabbitApplication

fun main(args: Array<String>) {
//    runApplication<RabbitApplication>(*args)
//    val app = SpringApplication(RabbitApplication::class.java)
//    app.run(*args)
    SpringApplication.run(RabbitApplication::class.java, *args)

    val context = ApplicationContextProvider.getContext()
    val base64Encoder = context.getBean(Base64Encoder::class.java)
    val urlEncoder = context.getBean(UrlEncoder::class.java)

    var encoder = Encoder(base64Encoder)
    val url = "www.naver.com/books/it?page=10&size=20&name=spring-boot"

    var result = encoder.encode(url)
    println("base64Encoder result: $result")

    encoder.setIEncoder(urlEncoder)
    result = encoder.encode(url)
    println("urlEncoder result2: $result")

    encoder = context.getBean("base64Encoder1", Encoder::class.java)
    result = encoder.encode(url);
    println("encoder result: $result")

    encoder = context.getBean("urlEncoder1", Encoder::class.java)
    result = encoder.encode(url);
    println("encoder result: $result")
    println(Base64.getEncoder().encodeToString("ansj@abc.com".toByteArray()))
}
