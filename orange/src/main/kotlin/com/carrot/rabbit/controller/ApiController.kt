package com.carrot.rabbit.controller

import com.carrot.rabbit.annotation.Decode
import com.carrot.rabbit.annotation.Timer
import com.carrot.rabbit.dto.PostRequest
import com.carrot.rabbit.dto.User2
import com.carrot.rabbit.dto.UserRequest
import org.springframework.web.bind.annotation.*
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@RestController
@RequestMapping("api")
class ApiController {
    @GetMapping("users")
    fun queryUsers(userRequest: UserRequest): String {
        return "${userRequest.name} / ${userRequest.email} / ${userRequest.age}"
    }

    @PostMapping("posts")
    fun queryPosts(@RequestBody requestData: Map<String, JvmType.Object>) {
        requestData.entries.forEach { entry ->
            println("${entry.key} / ${entry.value}")
        }
    }

    @PostMapping("posts2")
    fun queryPosts2(@RequestBody requestData: PostRequest) {
        println("$requestData")
    }

    @GetMapping("/get/{id}")
    fun get(@PathVariable id: Long, @RequestParam name: String): String {
        println("get method")
        println("get method: $id")
        println("get method: $name")
        return "$id $name"
    }

    @PostMapping("post")
    fun post(@RequestBody user: User2): User2 {
        println("post method: $user")
        return user;
    }

    @Timer
    @DeleteMapping("delete")
    fun delete() {
        try {
            Thread.sleep(1000 * 2)
        } catch (error: InterruptedException) {
            error.printStackTrace()
        }
    }

    @Decode
    @PutMapping("put")
    fun put(@RequestBody user: User2): User2 {
        println("put")
        println("user: $user")
        return user
    }
}