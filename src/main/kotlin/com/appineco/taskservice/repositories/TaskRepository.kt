package com.appineco.taskservice.repositories

import com.appineco.taskservice.entities.TaskEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TaskRepository : JpaRepository<TaskEntity, Long> {
}