package com.example.agenda.backend.repository

import com.example.agenda.backend.dao.TaskDao
import com.example.agenda.backend.entity.TaskEntity
import com.example.agenda.constants.TaskStatus
import com.example.agenda.model.TaskModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TasksRepository(
    private val dao: TaskDao
) {
    val getAllTasks get() = dao.getAllTasks()
    val getAllPendingTasks get() = dao.getTasksByStatus(TaskStatus.PENDING.value)
    val getAllInProgressTasks get() = dao.getTasksByStatus(TaskStatus.IN_PROGRESS.value)
    val getAllFinishedTasks get() = dao.getTasksByStatus(TaskStatus.FINISHED.value)

    suspend fun save(task: TaskModel) = withContext(IO) {
        dao.saveTask(task.toTaskEntity())
    }

    suspend fun delete(task: TaskModel) = withContext(IO) {
        dao.removeTask(task.toTaskEntity())
    }

    suspend fun update(task: TaskModel) = withContext(IO) {
        dao.updateTask(task.toTaskEntity())
    }

    fun searchTasks(query: String): Flow<List<TaskEntity>> {
        return dao.getTasksBySearch("%$query%")
    }
}

fun TaskModel.toTaskEntity() = TaskEntity(
    id = this.id,
    title = this.title,
    description = this.description,
    status = this.status,
    date = this.date
)

fun TaskEntity.toTaskModel() = TaskModel(
    id = this.id,
    title = this.title,
    description = this.description,
    status = this.status,
    date = this.date
)