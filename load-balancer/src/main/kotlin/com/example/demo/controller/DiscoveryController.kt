package com.example.demo.controller

import com.example.demo.service.LoadBalancerService
import com.example.demo.service.RegistrationService
import com.example.dto.ServiceDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.net.URI

/**
 * @author Evgeniy Loginov
 */
@RequestMapping("/discovery")
@RestController
class DiscoveryController(
    private val registrationService: RegistrationService,
    private val loadBalancerService: LoadBalancerService
) {

    @PostMapping
    fun register(@RequestBody serviceDto: ServiceDto): String {
        val service = registrationService.register(serviceDto.name, serviceDto.instanceId, serviceDto.uri)
        loadBalancerService.add(service.name, service.instanceId)
        return "ok"
    }

    @GetMapping("{serviceName}")
    fun getUri(@PathVariable("serviceName") serviceName: String): ResponseEntity<URI> {
        val instanceId = loadBalancerService.getNextInstance(serviceName)
        val service = registrationService.getByInstanceId(instanceId)
        return ResponseEntity.ok(service.uri)
    }


}
