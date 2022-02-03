package com.carrot.rabbit.util.encode

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

@Component
class Encoder(@Qualifier("urlEncoder0") private var iEncoder: IEncoder) {

    fun encode(message: String): String {
        return iEncoder.encode(message)
    }

    public fun setIEncoder(iEncoder: IEncoder) {
        this.iEncoder = iEncoder
    }

}