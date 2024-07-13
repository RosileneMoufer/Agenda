package com.example.agenda.viewmodel

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.agenda.constants.TaskStatus
import com.example.agenda.state.PagerStateUiState
import com.example.agenda.state.TaskFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@OptIn(ExperimentalFoundationApi::class)
class HomeViewModel: ViewModel() {

    private val _uiState: MutableStateFlow<PagerStateUiState> =
        MutableStateFlow(PagerStateUiState())
    val uiState = _uiState.asStateFlow()

    fun changeSubMenuSection(newPagerState: Int) {
        _uiState.update {it.copy(
            pagerState = newPagerState
        )}
    }



}