package com.example.agenda.backend.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.agenda.backend.data.Task

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveTask(task: Task)

    @Delete
    suspend fun removeTask(task: Task)

    @Update
    suspend fun updateTask(task: Task)

    // get Task
    @Query("SELECT * FROM table_tasks WHERE id= :id")
    fun getTask(id: Long) : Task

    // get all Tasks
    @Query("SELECT * FROM table_tasks")
    fun getAllTasks() : LiveData<List<Task>>
}