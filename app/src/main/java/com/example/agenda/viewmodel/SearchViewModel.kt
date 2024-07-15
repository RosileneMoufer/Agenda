package com.example.agenda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agenda.backend.entity.TaskEntity
import com.example.agenda.backend.repository.TasksRepository
import com.example.agenda.backend.repository.toTaskModel
import com.example.agenda.state.SearchUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SearchViewModel(
    private val repository: TasksRepository,
) : ViewModel() {
    private val _uiState: MutableStateFlow<SearchUiState> =
        MutableStateFlow(SearchUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onSearchChange = { search ->
                    _uiState.update {
                        it.copy(search = search)
                    }
                },
            )}
    }

    fun searchTasks(query: String) {
        viewModelScope.launch {
            repository.searchTasks(query).collect{ result ->
                updateTasks(result)
            }
        }
    }

    private fun updateTasks(result: List<TaskEntity>) {
        _uiState.update { it ->
            it.copy(
            tasks = result.map { it.toTaskModel() }
        )}
    }
}