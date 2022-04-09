package com.ansj.ioc.util

import org.springframework.stereotype.Component
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

@Component("UrlEncoder")
class UrlEncoder: IEncoder {
    override fun encode(message: String): String {
        println("urlEncoder encode method's called")
        return try{
            URLEncoder.encode(message, "UTF-8")
        }catch(error: UnsupportedEncodingException){
            "ERROR"
        }
    }
}