package com.example.demo.entity

import java.net.URI
import java.util.*

/**
 * @author Evgeniy Loginov
 */
data class Service(
    val name: String,
    val uri: URI,
    val instanceId: String = name + UUID.randomUUID()
) {

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }
        if (other is Service) {
            return instanceId == other.instanceId
        }
        return false
    }

    override fun hashCode(): Int {
        return instanceId.hashCode()
    }
}


