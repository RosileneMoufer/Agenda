package com.example.agenda.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.agenda.components.Divisor
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.button.StatusButton
import com.example.agenda.components.form.CalendarComponent
import com.example.agenda.components.form.DateComponent
import com.example.agenda.components.form.DescriptionComponent
import com.example.agenda.components.form.StatusComponent
import com.example.agenda.components.form.TitleComponent
import com.example.agenda.components.menu.TopBarNewTask
import com.example.agenda.constants.TaskStatus
import com.example.agenda.model.TaskModel
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.ui.theme.ButtonInactive
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.Title
import com.example.agenda.viewmodel.TaskFormViewModel
import kotlinx.coroutines.launch

@Composable
fun UpdateTaskScreen(
    task: TaskModel,
    taskFormViewModel: TaskFormViewModel,
    uiState: TaskFormUiState,
    navController: NavHostController
) {

    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current

    LaunchedEffect(task.id) {
        taskFormViewModel.setTheValueTaskToShowOnUpdateScreen(task)
    }

    Scaffold(
        modifier = Modifier
            .background(Secondary)
            .padding(16.dp),
        topBar = {
            TopBarNewTask(
                title = "Alterar Task",
                titleColor = Title,
                navController,
                taskFormViewModel
            )
        },
        bottomBar = {
            ActionButton("Alterar", Primary, Secondary) {
                scope.launch {
                    if ((uiState.title != "" && uiState.title.isNotBlank())
                        && (uiState.description != "" && uiState.description.isNotBlank())
                        && (uiState.date != "" && uiState.date.isNotBlank())) {
                        taskFormViewModel.update(task)
                        navController.popBackStack()
                    } else {
                        Toast.makeText(
                            ctx,
                            "Preencha todos os campos!",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        },
    ) { innitPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Secondary)
                .padding(innitPadding)
                .verticalScroll(rememberScrollState())
        ) {
            TitleComponent(uiState)

            Divisor()

            DescriptionComponent(uiState = uiState)

            Divisor()

            StatusComponent(uiState, taskFormViewModel)

            Divisor()

            DateComponent(uiState = uiState, formViewModel = taskFormViewModel)

            if ( taskFormViewModel.isOpenCalendar.value ) {
                CalendarComponent(taskFormViewModel)
            }
        }
    }
}