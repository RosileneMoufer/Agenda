package com.example.agenda.state

import androidx.compose.foundation.pager.PagerState
import com.example.agenda.constants.TaskStatus
import com.example.agenda.model.TaskModel

data class PagerStateUiState(
    val pagerState: Int = TaskStatus.PENDING.ordinal,
)