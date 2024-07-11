package com.example.agenda.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.agenda.backend.repository.TasksRepository
import com.example.agenda.constants.TaskStatus
import com.example.agenda.model.TaskModel
import com.example.agenda.state.TaskFormUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FormViewModel(
    private val repository: TasksRepository,
): ViewModel() {

    private val _uiState: MutableStateFlow<TaskFormUiState> =
        MutableStateFlow(TaskFormUiState())
    val uiState = _uiState.asStateFlow()

    init {
        _uiState.update { currentState ->
            currentState.copy(
                onTitleChange = { title ->
                    _uiState.update {
                        it.copy(title = title)
                    }
                },
                onDescriptionChange = { description ->
                    _uiState.update {
                        it.copy(description = description)
                    }
                },
                onDateChange = { date ->
                    _uiState.update {
                        it.copy(date = date)
                    }
                },
            )
        }
    }

    fun cleanForm() {
        _uiState.update {it.copy(
            title = "",
            description = "",
            status = TaskStatus.PENDING.value,
            date = ""
        )}
    }

    fun statusForm(status: String) {
        _uiState.update {it.copy(
            status = status
        )}
    }

    suspend fun save() {
        val task = TaskModel(
            title = _uiState.value.title,
            description = _uiState.value.description,
            status = _uiState.value.status,
            date = _uiState.value.date
        )
        viewModelScope.launch {
            repository.save(task)
        }
    }

    fun update(task: TaskModel) {
        viewModelScope.launch {
            repository.update(task)
        }
    }

    fun delete(task: TaskModel) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}