package com.example.demo.service

import java.util.*

@org.springframework.stereotype.Service
class LoadBalancerServiceInMemory : LoadBalancerService {

    //ServiceName on queue of instanceIds
    private val map = mutableMapOf<String, CircuitQueue>()

    override fun add(serviceName: String, instanceId: String) {
        val queue = map.getOrDefault(serviceName, CircuitQueue())
        queue.offer(instanceId)
    }

    override fun getNextInstance(serviceName: String): String {
        return Optional.ofNullable(map[serviceName])
            .map { it.next() }
            .orElseThrow { IllegalArgumentException("No service with name: $serviceName") }!!
    }
}

private class CircuitQueue {

    private val list: LinkedList<String> = LinkedList()
    private var index: Int = 0

    fun offer(instanceId: String) {
        if (!list.contains(instanceId)) {
            list.offer(instanceId)
        }
    }

    fun remove(instanceId: String) {
        list.remove(instanceId)
    }

    fun next(): String? {
        if (list.isEmpty()) {
            return null
        }
        if (list.size == 1) {
            return list[0]
        }

        if (index + 1 < list.size) {
            return list[++index]
        } else {
            index = 0
            return list[0]
        }
    }
}