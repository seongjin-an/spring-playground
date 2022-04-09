package com.ansj.ioc

import com.ansj.ioc.util.Encoder
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class IocApplication

fun main(args: Array<String>) {
    runApplication<IocApplication>(*args)
}
