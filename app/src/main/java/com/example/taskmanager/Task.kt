package com.example.taskmanager

import java.io.Serializable

data class Task(
    var title: String? = null,
    var desc: String? = null
) : Serializable