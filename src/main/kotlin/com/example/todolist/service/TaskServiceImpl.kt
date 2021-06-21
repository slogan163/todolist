package com.example.todolist.service

import com.example.todolist.entities.Task
import com.example.todolist.entities.TaskColor
import com.example.todolist.repository.TaskRepository
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

/**
 * @author Evgeniy Loginov
 */

@Service
class TaskServiceImpl(private val repository: TaskRepository): TaskService {

    override fun create(text: String, estimationDate: LocalDateTime, color: TaskColor): Task {
        val task = Task(text, LocalDateTime.now(), estimationDate, color)
        return repository.save(task)
    }

    override fun save(task: Task): Task {
        return repository.save(task)
    }

    override fun saveAll(tasks: List<Task>): List<Task> {
        return repository.saveAll(tasks).toList()
    }

    override fun findById(id: UUID): Task {
        return repository.findById(id).orElseThrow {
            IllegalArgumentException("Task with id '$id' not found")
        }
    }

    override fun findAll(): List<Task> {
        return repository.findAll().toList()
    }

    override fun findAll(estimationDate: LocalDate): List<Task> {
        return repository.findByEstimationDate(estimationDate)
    }

    override fun delete(id: UUID) {
        return repository.deleteById(id)
    }
}