package com.example.todolist.dto

import java.time.LocalDateTime
import java.util.*
import javax.validation.constraints.NotBlank

/**
 * @author Evgeniy Loginov
 */
data class TaskDto(
    var id: UUID?,
    @field:NotBlank // todo: Вернуть текст ошибки?
    var text: String,
    var estimationTime: LocalDateTime,
    var color: String,
    var userId: UUID
)
