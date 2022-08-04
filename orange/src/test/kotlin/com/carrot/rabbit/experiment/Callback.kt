package com.carrot.rabbit.experiment

interface Callback<U> {
    fun call(): U
}