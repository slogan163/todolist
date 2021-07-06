package com.example.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer

@EnableEurekaServer
@SpringBootApplication
class LoadBalancerApplication

fun main(args: Array<String>) {
    runApplication<LoadBalancerApplication>(*args)
}
