package com.appineco.taskservice.controllers

import com.appineco.taskservice.models.Task
import com.appineco.taskservice.services.TaskService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/tasks")
class TaskController (val taskService: TaskService) {

    @GetMapping("/{id}")
    fun getTaskById(@PathVariable id: Long): Task {
        return taskService.getTaskById(id)
    }

    @GetMapping("/")
    fun getAllTasks(): List<Task> {
        return taskService.getAllTasks()
    }

    @PostMapping("/create")
    fun createTask(@RequestBody newTask: Task): Task {
        return taskService.createTask(newTask)
    }

    @PutMapping("/{id}")
    fun updateTask(@PathVariable id: Long, @RequestBody updatedTask: Task): Task {
        return taskService.updateTask(id, updatedTask)
    }

    @DeleteMapping("/{id}")
    fun deleteTask(@PathVariable id: Long) {
        taskService.deleteTask(id)
    }
}