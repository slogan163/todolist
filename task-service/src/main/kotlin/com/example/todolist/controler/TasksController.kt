package com.example.todolist.controler

import com.example.todolist.dto.TaskDto
import com.example.todolist.entities.Task
import com.example.todolist.entities.TaskColor
import com.example.todolist.service.TaskServiceImpl
import org.springframework.format.annotation.DateTimeFormat
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import org.springframework.http.HttpStatus
import java.io.Serializable
import java.time.LocalDate
import javax.validation.Valid


/**
 * @author Evgeniy Loginov
 */
@RequestMapping("/tasks")
@RestController
class TasksController(private val taskService: TaskServiceImpl) {

    //todo обработка ошибок

    @GetMapping("/{id}")
    fun get(@PathVariable("id") id: UUID): ResponseEntity<TaskDto> {
        return ResponseEntity.ok(taskService.findById(id).convertToDto())
    }

    @GetMapping
    fun getByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): ResponseEntity<List<TaskDto>> {
        return ResponseEntity.ok(taskService.findAll(date).convertToDtos())
    }

    @GetMapping("/all")
    fun getAllTasks(): ResponseEntity<List<TaskDto>> {
        val tasks = taskService.findAll()
        return ResponseEntity.ok(tasks.convertToDtos())
    }

    @PutMapping
    fun create(@Valid @RequestBody taskDto: TaskDto): ResponseEntity<TaskDto> {
        val newTask = taskService.create(taskDto.text, taskDto.estimationTime, TaskColor.valueOf(taskDto.color), taskDto.userId)
        return ResponseEntity.ok(newTask.convertToDto())
    }

    @PostMapping
    fun save(@Valid @RequestBody taskDto: TaskDto): ResponseEntity<TaskDto> {
        val task = taskService.save(taskDto.convertToTask())
        return ResponseEntity.ok(task.convertToDto())
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

    private fun TaskDto.convertToTask() = Task(
        this.id,
        this.text,
        estimationTime = this.estimationTime,
        color = TaskColor.valueOf(this.color),
        userId = this.userId
    )

    private fun Task.convertToDto(): TaskDto =
        TaskDto(this.id, this.text, this.estimationTime, this.color.toString(), this.userId)

    private fun List<Task>.convertToDtos(): List<TaskDto> = this.map { it.convertToDto() }
}