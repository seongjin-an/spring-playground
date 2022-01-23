package com.carrot.rabbit.dto

class User(
    val name: String = "",
    val age: Int = 0
) {
    override fun toString(): String {
        return "User(name='$name', age=$age)"
    }
}