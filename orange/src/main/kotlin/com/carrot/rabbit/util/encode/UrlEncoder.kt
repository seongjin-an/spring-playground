package com.carrot.rabbit.util.encode

import org.springframework.stereotype.Component
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

@Component("urlEncoder0")
class UrlEncoder: IEncoder {
    override fun encode(message: String): String {
        return try{
            URLEncoder.encode(message, "UTF-8")
        }catch(error: UnsupportedEncodingException){
            error.printStackTrace()
            "error"
        }
    }
}