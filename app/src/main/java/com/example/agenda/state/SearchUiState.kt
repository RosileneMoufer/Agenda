package com.example.agenda.state

import com.example.agenda.model.TaskModel

data class SearchUiState (
    val search: String = "",
    val tasks: List<TaskModel> = emptyList(),

    val onSearchChange: (String) -> Unit = {},
    val onTasksChange: (TaskModel) -> Unit = {},
)