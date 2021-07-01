package com.example.todolist.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.*

/**
 * @author Evgeniy Loginov
 */
@Entity
@Table(name = "tasks")
data class Task(
        @Id
        @GeneratedValue
        var id: UUID? = null,
        var text: String,
        var creationTime: LocalDateTime = LocalDateTime.now(),
        var estimationTime: LocalDateTime,
        var color: TaskColor,
        var userId: UUID
)

enum class TaskColor {
    GREEN, RED
}
