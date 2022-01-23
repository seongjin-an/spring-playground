package com.carrot.rabbit.config

import com.carrot.rabbit.util.encode.Base64Encoder
import com.carrot.rabbit.util.encode.Encoder
import com.carrot.rabbit.util.encode.UrlEncoder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {
    @Bean("base64Encoder1")
    fun encoder(base64Encoder: Base64Encoder): Encoder {
        return Encoder(base64Encoder)
    }
    @Bean("urlEncoder1")
    fun encoder(urlEncoder: UrlEncoder): Encoder{
        return Encoder(urlEncoder)
    }
}