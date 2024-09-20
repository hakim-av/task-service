package com.appineco.taskservice.services

import com.appineco.taskservice.entities.TaskEntity
import com.appineco.taskservice.models.Task
import com.appineco.taskservice.repositories.TaskRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException

@Service
class TaskService (val taskRepository: TaskRepository) {

    fun getTaskById(id: Long): Task {
        return taskRepository.findById(id)
            .map {
                Task(id = it.id!!, name = it.name, description = it.description, done = it.done)
            }
            .orElseThrow {
                ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id $id not found")
            }
    }

    fun getAllTasks(): List<Task> {
        return taskRepository.findAll().map {
            Task(id = it.id!!, name = it.name, description = it.description, done = it.done)
        }
    }

    fun createTask(newTask: Task): Task {
        val save = taskRepository.save(TaskEntity(id = null, name = newTask.name, description = newTask.description, done = newTask.done))
        return Task(id = save.id!!, name = save.name, description = save.description, done = save.done)
    }

    fun updateTask(id: Long, updatedTask: Task): Task {
        return taskRepository.findById(id).map {
            val save = taskRepository.save(TaskEntity(id = it.id, name = updatedTask.name, description = updatedTask.description, done = updatedTask.done))
            Task(id = save.id!!, name = save.name, description = save.description, done = save.done)
        }.orElseThrow {
            ResponseStatusException(HttpStatus.NOT_FOUND, "Task with id $id not found")
        }
    }

    fun deleteTask(id: Long) {
        taskRepository.deleteById(id)
    }
}