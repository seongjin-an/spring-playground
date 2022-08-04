package com.carrot.rabbit.experiment

class CallbackService {
    fun test(callback: Callback<String>): String{
        return callback.call()
    }

    fun test2(callback: (String) -> Unit) {
        val name = "ansj!!!"
        callback(name)
    }

    fun test3(callback: CallbackV2) {
        callback.call{
            println("it: $it")
        }
    }
}