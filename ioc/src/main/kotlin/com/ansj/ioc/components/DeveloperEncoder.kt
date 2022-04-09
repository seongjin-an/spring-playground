package com.ansj.ioc.components

import com.ansj.ioc.util.Encoder
import com.ansj.ioc.util.UrlEncoder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class DeveloperEncoder(
    @Qualifier("UrlEncoder") val urlEncoder: UrlEncoder
) {
    fun encode(): String{
        val encoder = Encoder(urlEncoder)

        val url = "www.naver.com"

        return encoder.encode(url)
    }
}