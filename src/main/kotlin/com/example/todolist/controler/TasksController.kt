package com.example.todolist.controler

import com.example.todolist.entities.Task
import com.example.todolist.entities.TaskColor
import com.example.todolist.service.TaskServiceImpl
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import java.util.*
import org.springframework.http.HttpStatus
import java.io.Serializable
import java.time.LocalDate


/**
 * @author Evgeniy Loginov
 */
@RequestMapping("/tasks")
@RestController
class TasksController(private val taskService: TaskServiceImpl) {

    //todo обработка ошибок

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: UUID): ResponseEntity<Task> {
        return ResponseEntity.ok(taskService.findById(id))
    }

    @GetMapping
    fun getByDate(@RequestParam("date") date: String): ResponseEntity<List<Task>> {
        val localDate = LocalDate.parse(date)
        return ResponseEntity.ok(taskService.findAll(localDate))
    }

    @GetMapping("/all")
    fun getAllTasks(): ResponseEntity<List<Task>> {
        val tasks = taskService.findAll()
        return ResponseEntity.ok(tasks)
    }

    @PutMapping
    fun create(@RequestBody params: Map<String, Any>): ResponseEntity<Task> {
        val text: String = params["text"].toString()
        val estimationDate: LocalDateTime = LocalDateTime.parse(params["estimationDate"].toString())
        val color = TaskColor.valueOf(params["color"].toString())

        return ResponseEntity.ok(taskService.create(text, estimationDate, color))
    }

    @PostMapping
    fun save(@RequestBody task: Task): Task {
        return taskService.save(task)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable("id") id: UUID): ResponseEntity<Serializable> {
        return try {
            taskService.delete(id)
            ResponseEntity(id, HttpStatus.OK)
        } catch (e: Exception) {
            ResponseEntity(e.localizedMessage, HttpStatus.NOT_FOUND)
        }

    }
}