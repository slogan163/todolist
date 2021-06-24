package com.example.todolist.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * @author Evgeniy Loginov
 */
@Entity
@Table(name = "task")
data class Task(
        @Id
        @GeneratedValue
        var id: UUID? = null,
        var text: String,
        var creationTime: LocalDateTime = LocalDateTime.now(),
        var estimationTime: LocalDateTime,
        var color: TaskColor
)

enum class TaskColor {
    GREEN, RED
}
