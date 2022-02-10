package com.dynamic.routing

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class RoutingApplication

fun main(args: Array<String>) {
//    runApplication<RoutingApplication>(*args)
    val apiHome = System.getenv("API_HOME")
    SpringApplicationBuilder(RoutingApplication::class.java)
        .properties(
            "spring.config.location="+
                    "file:$apiHome/properties/application.yml"+
                    ", file:$apiHome/properties/database.yml"
        ).application().run(*args)
}
