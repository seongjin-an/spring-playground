package com.carrot.rabbit.dto

import com.fasterxml.jackson.annotation.JsonProperty

class PostRequest(
    val account: String,
    val email: String,
    val address: String,
    val password: String,
    @JsonProperty("phone_number")
    val phoneNumber: String
) {
    override fun toString(): String {
        return "PostRequest(account='$account', email='$email', address='$address', password='$password', phoneNumber='$phoneNumber')"
    }
}