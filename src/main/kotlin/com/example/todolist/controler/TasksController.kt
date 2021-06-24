package com.example.todolist.controler

import com.example.todolist.controler.dto.TaskDtoUtils.convertDtoToTask
import com.example.todolist.controler.dto.TaskDtoUtils.convertTaskToDto
import com.example.todolist.controler.dto.TaskDtoUtils.convertTasksToDto
import com.example.todolist.controler.dto.TaskDto
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
        return ResponseEntity.ok(convertTaskToDto(taskService.findById(id)))
    }

    @GetMapping
    fun getByDate(@RequestParam("date") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate): ResponseEntity<List<TaskDto>> {
        return ResponseEntity.ok(convertTasksToDto(taskService.findAll(date)))
    }

    @GetMapping("/all")
    fun getAllTasks(): ResponseEntity<List<TaskDto>> {
        val tasks = taskService.findAll()
        return ResponseEntity.ok(convertTasksToDto(tasks))
    }

    @PutMapping
    fun create(@Valid @RequestBody taskDto: TaskDto): ResponseEntity<TaskDto> {
        val newTask = taskService.create(taskDto.text, taskDto.estimationTime, TaskColor.valueOf(taskDto.color))
        return ResponseEntity.ok(convertTaskToDto(newTask))
    }

    @PostMapping
    fun save(@Valid @RequestBody taskDto: TaskDto): TaskDto {
        val task = taskService.save(convertDtoToTask(taskDto))
        return convertTaskToDto(task)
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