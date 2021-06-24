package com.example.todolist.controler.dto

import com.example.todolist.entities.Task
import com.example.todolist.entities.TaskColor

/**
 * @author Evgeniy Loginov
 */
object TaskDtoUtils {

    fun convertDtoToTask(taskDto: TaskDto): Task =
        Task(taskDto.id, taskDto.text, estimationTime = taskDto.estimationTime, color = TaskColor.valueOf(taskDto.color))

    fun convertTaskToDto(task: Task): TaskDto = TaskDto(task.id, task.text, task.estimationTime, task.color.toString())
    fun convertTasksToDto(tasks: List<Task>): List<TaskDto> = tasks.map { convertTaskToDto(it) }
}