package com.example.agenda.state

import com.example.agenda.model.TaskModel

data class SearchStateUiState (
    val search: String = "",
    val tasks: List<TaskModel> = emptyList(),

    val onSearchChange: (String) -> Unit = {},
    val onTasksChange: (TaskModel) -> Unit = {},
)