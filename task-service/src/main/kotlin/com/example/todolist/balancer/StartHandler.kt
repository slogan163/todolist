package com.example.todolist.balancer

import com.example.dto.ServiceDto
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate
import java.net.URI

/**
 * @author Evgeniy Loginov
 */
@Component
class StartHandler(val restTemplate: RestTemplate) {

    private val logger = LoggerFactory.getLogger(StartHandler::class.java)

    @Value("\${LOAD_BALANCER_HOST}")
    lateinit var loadBalancerHost: String

    @Value("\${INSTANCE_ID}")
    lateinit var instanceId: String

    @EventListener(ApplicationReadyEvent::class)
    fun tryToRegisterInLoadBalancer() {
        println("App started")

        val uri = URI.create(loadBalancerHost)
        var response: String? = null

        while (response != "ok") {
            try {
                val uri = URI.create("http://task-service:8080/")
                val dto = ServiceDto(uri, "task-service", instanceId)
                response = restTemplate.postForEntity(URI.create(loadBalancerHost), dto, String::class.java)
                    .toString()
            } catch (e: Exception) {
                logger.error(e.stackTraceToString())
                println(e.printStackTrace())
            } finally {
                Thread.sleep(5000)
            }
        }
    }
}

