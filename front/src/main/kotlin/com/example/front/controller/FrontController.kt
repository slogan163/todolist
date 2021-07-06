package com.example.front.controller

import com.example.todolist.dto.TaskDto
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

    @GetMapping("/tasks")
    fun getAllTasks(): ResponseEntity<List<TaskDto>> {
        val tasks = restTemplate.getForEntity(URI.create("http://task-service/tasks/all"), Any::class.java) as ResponseEntity<List<TaskDto>>
        return ResponseEntity.ok(tasks.body)
    }
}