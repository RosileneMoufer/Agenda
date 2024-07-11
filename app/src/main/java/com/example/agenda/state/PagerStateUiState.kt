package com.example.agenda.state

import com.example.agenda.constants.TaskStatus
import com.example.agenda.model.TaskModel

data class PagerStateUiState(
    val state: String = TaskStatus.PENDING.value,
)