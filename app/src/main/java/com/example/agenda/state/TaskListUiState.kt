package com.example.agenda.state

import com.example.agenda.model.TaskModel

data class TasksListUiState(
    val tasks: List<TaskModel> = emptyList(),
    val onTasksChange: (TaskModel) -> Unit = {},
    val pendingTasks: List<TaskModel> = emptyList(),
    val onPendingTaskChange: (TaskModel) -> Unit = {},
    val inProgressTasks: List<TaskModel> = emptyList(),
    val onInProgressTasksChange: (TaskModel) -> Unit = {},
    val finishedTasks: List<TaskModel> = emptyList(),
    val onFinishedTasksChange: (TaskModel) -> Unit = {},
)