package com.appineco.taskservice.controllers

import com.appineco.taskservice.TaskServiceApplication
import com.appineco.taskservice.models.Task
import org.junit.jupiter.api.Assertions.*
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.client.TestRestTemplate
import org.springframework.http.HttpStatus
import kotlin.test.Test

@SpringBootTest(
    classes = [TaskServiceApplication::class],
    webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
class TaskControllerTest (
    @Autowired var restTemplate: TestRestTemplate
) {

    var taskId: Long = 0

    @Test
    fun createTask() {
        val task = Task(null,"Task", "description", false)
        val result = this.restTemplate.postForEntity("/tasks/create", task, Task::class.java)
        taskId = result.body?.id!!
        assertTrue { result.body?.name.equals("Task") }
        assertTrue { result.body?.description.equals("description") }
    }

    @Test
    fun returnTaskSuccessfully() {
        createTask()
        val result = this.restTemplate.getForEntity("/tasks/{id}", Task::class.java, taskId)
        assertTrue { result.body?.name.equals("Task") }
    }

    @Test
    fun deleteTaskSuccessfully() {
        createTask()
        this.restTemplate.delete("/tasks/{id}", taskId)
        val result = this.restTemplate.getForEntity("/tasks/{id}", String::class.java, taskId)
        assertTrue { result.statusCode.equals(HttpStatus.NOT_FOUND) }
    }

    @Test
    fun putTaskSuccessfully() {
        createTask()
        val task = Task(null,"Task", "description", true)
        this.restTemplate.put("/tasks/{id}", task, taskId)
        val result = this.restTemplate.getForEntity("/tasks/{id}", Task::class.java, taskId)
        assertTrue { result.statusCode.is2xxSuccessful }
        assertTrue { result.body?.done!! }
    }
}