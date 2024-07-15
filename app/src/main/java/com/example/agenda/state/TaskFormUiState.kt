package com.example.agenda.state

import androidx.compose.runtime.mutableStateOf
import com.example.agenda.constants.TaskStatus

data class TaskFormUiState(
    var title: String = "",
    var description: String = "",
    var status: String = TaskStatus.PENDING.value,
    var date: String = "",

    val onTitleChange: (String) -> Unit = {},
    val onDescriptionChange: (String) -> Unit = {},
    val onDateChange: (String) -> Unit = {},

    val isOpenCalendar: Boolean = false
)
