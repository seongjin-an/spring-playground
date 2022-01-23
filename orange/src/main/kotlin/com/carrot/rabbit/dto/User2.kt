package com.carrot.rabbit.dto

class User2(
    val id: String,
    val pw: String,
    var email: String
) {
    override fun toString(): String {
        return "User2(id='$id', pw='$pw', email='$email')"
    }
}