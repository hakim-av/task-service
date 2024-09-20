package com.appineco.taskservice.models

data class Task(
    var id: Long? = null,
    var name: String,
    var description: String,
    var done: Boolean
)