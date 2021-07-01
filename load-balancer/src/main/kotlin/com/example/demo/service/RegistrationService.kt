package com.example.demo.service

import com.example.demo.entity.Service
import java.net.URI

/**
 * @author Evgeniy Loginov
 */
interface RegistrationService {

    fun register(name: String, instanceId: String, uri: URI): Service

    fun remove()

    fun getByName(name: String) : Set<Service>
    fun getByInstanceId(instanceId: String): Service
}