package com.example.agenda.viewmodel

import androidx.compose.runtime.mutableStateOf
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

class TaskFormViewModel(
    private val repository: TasksRepository,
): ViewModel() {

    var isOpenCalendar = mutableStateOf( false )

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

    fun setTheValueTaskToShowOnUpdateScreen(task: TaskModel) {
        _uiState.value.title = task.title
            _uiState.value.description = task.description
            _uiState.value.status = task.status
            _uiState.value.date = task.date
    }

    fun cleanForm() {
        _uiState.update {it.copy(
            title = "",
            description = "",
            status = TaskStatus.PENDING.value,
            date = ""
        )}
    }

    fun setStatusValue(newStatus: String) {
        _uiState.update {it.copy(
            status = newStatus
        )}
    }

    fun setDateValue(newDate: String) {
        _uiState.update {it.copy(
            date = newDate
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
        val _task = TaskModel(
            id = task.id,
            title = _uiState.value.title,
            description = _uiState.value.description,
            status = _uiState.value.status,
            date = _uiState.value.date
        )

        viewModelScope.launch {
            repository.update(_task)
        }
    }

    fun delete(task: TaskModel) {
        viewModelScope.launch {
            repository.delete(task)
        }
    }
}