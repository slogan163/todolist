package com.example.front.controller

import com.example.todolist.dto.TaskDto
import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate
import java.net.URI

/**
 * @author Evgeniy Loginov
 */
@RequestMapping("/front")
@RestController
class FrontController(private val restTemplate: RestTemplate) {

    @Value("\${LOAD_BALANCER_HOST}")
    private lateinit var LOAD_BALANCER_HOST: String

    @GetMapping("/tasks")
    fun getAllTasks(): ResponseEntity<List<TaskDto>> {
        val taskServiceUri = restTemplate.getForEntity(URI.create("$LOAD_BALANCER_HOST/task-service"), URI::class.java)
        val tasks = restTemplate.getForEntity(URI.create("${taskServiceUri.body.toString()}tasks/all"), Any::class.java) as ResponseEntity<List<TaskDto>>
        return ResponseEntity.ok(tasks.body)
    }
}