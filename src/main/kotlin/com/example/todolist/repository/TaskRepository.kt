package com.example.todolist.repository

import com.example.todolist.entities.Task
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.time.LocalDate
import java.util.*

/**
 * @author Evgeniy Loginov
 */
interface TaskRepository : JpaRepository<Task, UUID> {

    @Query("select t from Task t where t.estimationTime >= :#{#date.atStartOfDay()} and t.estimationTime < :#{#date.plusDays(1).atStartOfDay()}")
    fun findByEstimationDate(date: LocalDate): List<Task>

    //todo: pagination
}

