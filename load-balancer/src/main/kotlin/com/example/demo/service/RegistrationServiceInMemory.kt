package com.example.demo.service

import com.example.demo.entity.Service
import java.net.URI

@org.springframework.stereotype.Service
class RegistrationServiceInMemory : RegistrationService {

    private val table = mutableSetOf<Service>()

    override fun register(name: String, instanceId: String, uri: URI): Service {
        val service = Service(name, uri, instanceId)
        table.add(service)
        return service
    }

    override fun getByInstanceId(instanceId: String): Service {
        return table.first { it.instanceId == instanceId }
    }

    override fun remove() {
        TODO("Not yet implemented")
    }

    override fun getByName(name: String): Set<Service> {
        return table.filter { it.name == name }.toSet()
    }
}