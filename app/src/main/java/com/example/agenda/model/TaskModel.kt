package com.example.agenda.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TaskModel(
    val id: Long = 0,
    val title: String,
    val description: String,
    val status: String,
    val date: String
) : Parcelable