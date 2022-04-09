package com.ansj.ioc.util

import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component

class Encoder(val iEncoder: IEncoder) {
    fun encode(message: String): String{
        return iEncoder.encode(message)
    }
}