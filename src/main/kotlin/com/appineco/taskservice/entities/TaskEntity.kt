package com.appineco.taskservice.entities

import jakarta.persistence.*

@Entity
@Table(schema = "task_service", name = "task")
class TaskEntity (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String,
    var description: String,
    var done: Boolean
)