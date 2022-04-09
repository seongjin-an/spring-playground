package com.ansj.ioc.util

import org.springframework.stereotype.Component
import java.util.Base64

@Component("Base64Encoder")
class Base64Encoder: IEncoder {
    override fun encode(message: String): String {
        println("base64Encoder encode method's called")
        val byteArray = message.encodeToByteArray()
        val result = Base64.getEncoder().encodeToString(byteArray)
        return result
    }
}