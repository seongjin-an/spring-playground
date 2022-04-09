package com.ansj.ioc.components

import com.ansj.ioc.util.Base64Encoder
import com.ansj.ioc.util.Encoder
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class PersonEncoder(
    @Qualifier("Base64Encoder") val base64Encoder: Base64Encoder
) {
    fun encode(): String{
        val encoder = Encoder(base64Encoder);

        val url = "www.naver.com"

        return encoder.encode(url)
    }
}