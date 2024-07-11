package com.example.agenda.backend.repository

import com.example.agenda.backend.dao.TaskDao
import com.example.agenda.backend.entity.TaskEntity
import com.example.agenda.model.TaskModel
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class TasksRepository(
    private val dao: TaskDao
) {
    val tasks get() = dao.getAllTasks()

    suspend fun save(task: TaskModel) = withContext(IO) {
        dao.saveTask(task.toTaskEntity())
    }

    suspend fun delete(task: TaskModel) = withContext(IO) {
        dao.removeTask(task.toTaskEntity())
    }

    suspend fun update(task: TaskModel) = withContext(IO) {
        dao.updateTask(task.toTaskEntity())
    }

    fun getTask(id: Long) {
        dao.getTask(id)
    }

    fun getAllPendingTasks(status: String) {
        dao.getAllPendingTasks(status)
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