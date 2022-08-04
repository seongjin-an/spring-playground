package com.carrot.rabbit.experiment

import org.junit.jupiter.api.Test

class Template {

    @Test
    fun tmep(){
        val callbackService = CallbackService()
        val obj = object : Callback<String> {
            override fun call(): String {
                println("hello !!")
                return "ansj"
            }
        }
        val test = callbackService.test(obj)
        println("test: $test")

        val name = "ansj"

        callbackService.test2 {
            println("it: $it")
        }

        callbackService.test3(object: CallbackV2 {
            override fun call(arg: (String) -> Unit) {
                val name = "ansj333"
                arg(name)
            }
        })
    }
}