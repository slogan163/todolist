package com.example.todolist.entities

import java.time.LocalDateTime
import java.util.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

/**
 * @author Evgeniy Loginov
 */
@Entity
data class Task(
        var text: String,
        var creationTime: LocalDateTime,
        var estimationTime: LocalDateTime,
        var color: TaskColor
) {
    @Id
    @GeneratedValue
    var id: UUID? = null
}

enum class TaskColor {
    GREEN, RED
}
