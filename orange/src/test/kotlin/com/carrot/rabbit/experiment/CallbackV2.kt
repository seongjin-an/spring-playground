package com.carrot.rabbit.experiment

interface CallbackV2 {
    fun call(arg: (String) -> Unit)
}