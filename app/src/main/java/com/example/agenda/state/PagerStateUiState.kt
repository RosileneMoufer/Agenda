package com.example.agenda.state

import com.example.agenda.constants.TaskStatus

data class PagerStateUiState (
    val pagerState: Int = TaskStatus.PENDING.ordinal,
)