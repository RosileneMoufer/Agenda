package com.example.agenda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agenda.backend.entity.TaskEntity
import com.example.agenda.backend.repository.TasksRepository
import com.example.agenda.backend.repository.toTaskModel
import com.example.agenda.model.TaskModel
import com.example.agenda.state.SearchStateUiState
import com.example.agenda.state.TaskFormUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: TasksRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<SearchStateUiState> =
        MutableStateFlow(SearchStateUiState())
    val uiState = _uiState.asStateFlow()

    fun searchTasks(query: String) {
        viewModelScope.launch {
            repository.searchTasks(query).collect{ result ->
                updateTasks(result)
            }
        }
    }

    private fun updateTasks(result: List<TaskEntity>) {
        _uiState.update {it.copy(
            tasks = result.map { it.toTaskModel() }
        )}
    }
}