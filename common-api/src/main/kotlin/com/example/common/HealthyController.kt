package com.example.common

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 * @author Evgeniy Loginov
 */
@RequestMapping("/healthy")
@RestController
class HealthyController {

    @Value("\${instanceId}")
    lateinit var instanceId: String

    @GetMapping
    fun healthy(): String {
        return "healthy: $instanceId"
    }
}