package com.example.demo.service

interface LoadBalancerService {
    fun getNextInstance(serviceName: String): String

    fun add(serviceName: String, instanceId: String)
}
