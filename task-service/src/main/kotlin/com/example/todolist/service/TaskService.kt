package com.example.todolist.service

import com.example.todolist.entities.Task
import com.example.todolist.entities.TaskColor
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

interface TaskService {

    @Transactional
    fun create(text: String, estimationDate: LocalDateTime, color: TaskColor, userId: UUID): Task

    @Transactional
    fun save(task: Task): Task

    @Transactional
    fun saveAll(tasks: List<Task>): List<Task>

    @Transactional
    fun findById(id: UUID): Task

    @Transactional
    fun findAll(): List<Task>

    @Transactional
    fun findAll(estimationDate: LocalDate): List<Task>

    @Transactional
    fun delete(id: UUID)
}
