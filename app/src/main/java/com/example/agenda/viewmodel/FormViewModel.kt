package com.example.agenda.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class FormViewModel: ViewModel() {
    private val _title = mutableStateOf("")
    private val _description = mutableStateOf("")
    private val _status = mutableStateOf("")
    private val _hour = mutableStateOf("")

    var title: MutableState<String> = _title
    var description: MutableState<String> = _description
    var status: MutableState<String> = _status
    var hour: MutableState<String> = _hour

    /*
    fun CleanForm() {

    }

     */
}