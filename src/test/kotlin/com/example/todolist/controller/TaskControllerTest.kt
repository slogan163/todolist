package com.example.todolist.controller

import com.example.todolist.controler.TasksController
import com.example.todolist.entities.Task
import com.example.todolist.entities.TaskColor
import com.example.todolist.service.TaskServiceImpl
import org.json.JSONArray
import org.json.JSONObject
import org.junit.jupiter.api.Test
import org.mockito.Mockito
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.time.LocalDateTime
import java.util.*


/**
 * @author Evgeniy Loginov
 */
@WebMvcTest(TasksController::class)
class TaskControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc
    @MockBean
    private lateinit var taskService: TaskServiceImpl

    @Test
    fun testCreate() {

        Mockito.doReturn(Task(id, text, LocalDateTime.now(), estimationTime, taskColor))
            .`when`(taskService).create(text, estimationTime, taskColor)

        val taskJson = """{"text": "$text", "estimationTime": "$estimationTime", "color": "$color"}"""

        mockMvc.perform(
            put("/tasks")
                .content(taskJson)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(status().isOk)
            .andExpect(jsonPath("$.id").value(id.toString()))
            .andExpect(jsonPath("$.text").value(text))
            .andExpect(jsonPath("$.estimationTime").value(estimationTimeString))
            .andExpect(jsonPath("$.color").value(color))


    }

    @Test
    fun testGetTasksByEstimationDate() {
        Mockito.doReturn(listOf(task))
            .`when`(taskService).findAll(estimationDate)

        val jsonResponse = mockMvc.perform(get("/tasks?date=$estimationDate"))
            .andExpect(status().isOk)
            .andReturn().response.contentAsString

        val taskResponse = JSONArray(jsonResponse)[0] as JSONObject
        val estimationTimeResponse = LocalDateTime.parse(taskResponse.get("estimationTime") as String)
        assert(estimationDate == estimationTimeResponse.toLocalDate())

    }

    companion object {
        val id = UUID.randomUUID()
        val text = "text124"
        val estimationTimeString = "2021-06-18T07:36:56.195"
        val estimationTime = LocalDateTime.parse(estimationTimeString)
        val estimationDate = estimationTime.toLocalDate()
        val color = "GREEN"
        val taskColor = TaskColor.valueOf(color)

        val task = Task(id, text, LocalDateTime.now(), estimationTime, taskColor)
    }
}