package com.example.agenda.viewmodel

import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.agenda.constants.TaskStatus
import com.example.agenda.state.PagerStateUiState
import com.example.agenda.state.TaskFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel: ViewModel() {
    //val pagerState = rememberPagerState(pageCount = { 3 })

    var pagerState = mutableStateOf(TaskStatus.PENDING.value)

    /*
    private val _uiState = MutableStateFlow<PagerStateUiState(TaskStatus.PENDING.value)> =
        MutableStateFlow(PagerStateUiState())

    val uiState = _uiState.asStateFlow()*/
}