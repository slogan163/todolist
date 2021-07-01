package com.example.dto

import java.net.URI

/**
 * @author Evgeniy Loginov
 */
data class ServiceDto(
    val uri: URI,
    val name: String,
    val instanceId: String
)


