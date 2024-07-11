package com.example.agenda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.agenda.backend.repository.TasksRepository
import com.example.agenda.backend.repository.toTaskModel
import com.example.agenda.constants.TaskStatus
import com.example.agenda.state.TasksListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class TasksListViewModel(
    private val repository: TasksRepository,
    //private val viewModel: HomeViewModel
) : ViewModel() {

    private val _uiState: MutableStateFlow<TasksListUiState> =
        MutableStateFlow(TasksListUiState())

    val uiState get() = _uiState
            .combine(repository.getAllTasks) { uiState, tasks ->
                uiState.copy(tasks = tasks.map { it.toTaskModel() })
            }

}