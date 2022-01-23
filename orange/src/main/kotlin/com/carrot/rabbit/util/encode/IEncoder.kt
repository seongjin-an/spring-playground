package com.carrot.rabbit.util.encode

interface IEncoder {
    fun encode(message: String): String
}