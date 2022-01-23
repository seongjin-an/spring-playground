package com.carrot.rabbit.util.encode

import org.springframework.stereotype.Component
import java.util.*

@Component("base64Encoder0")
class Base64Encoder: IEncoder {
    override fun encode(message: String): String {
        return Base64.getEncoder().encodeToString(message.toByteArray())
    }
}