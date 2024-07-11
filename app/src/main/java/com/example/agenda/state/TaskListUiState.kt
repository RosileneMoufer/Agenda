package com.example.agenda.state

import com.example.agenda.model.TaskModel

data class TasksListUiState(
    val tasks: List<TaskModel> = emptyList(),
    val onTaskDoneChange: (TaskModel) -> Unit = {},
)