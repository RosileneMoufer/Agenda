package com.example.agenda.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.components.Divisor
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.form.CalendarComponent
import com.example.agenda.components.form.DateComponent
import com.example.agenda.components.form.DescriptionComponent
import com.example.agenda.components.form.StatusComponent
import com.example.agenda.components.menu.TopBarNewTask
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.StrokeForm
import com.example.agenda.ui.theme.Title
import com.example.agenda.viewmodel.TaskFormViewModel
import kotlinx.coroutines.launch

@Composable
fun NewTaskScreen(
    navController: NavController,
    formViewModel: TaskFormViewModel,
    uiState: TaskFormUiState
) {
    val scope = rememberCoroutineScope()
    val ctx = LocalContext.current

    Scaffold(
        modifier = Modifier
            .background(Secondary)
            .padding(16.dp),
        topBar = {
            TopBarNewTask(
                title = "Criar Task",
                titleColor = Title,
                navController,
                formViewModel
            )
        },
        bottomBar = {
            ActionButton("Criar", Primary, Secondary) {
                scope.launch {
                    if ((uiState.title != "" && uiState.title.isNotBlank())
                        && (uiState.description != "" && uiState.description.isNotBlank())
                        && (uiState.date != "" && uiState.date.isNotBlank())) {
                        formViewModel.save()
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
            TextField(
                value = uiState.title,
                onValueChange = uiState.onTitleChange,
                label = {
                    Text(
                        text = "Título",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Secondary,
                    unfocusedBorderColor = Secondary,
                    unfocusedTextColor = Title,
                    focusedContainerColor = Secondary,
                    focusedBorderColor = StrokeForm,
                    focusedLabelColor = Title,
                ),
                modifier = Modifier.fillMaxWidth()
            )

            Divisor()

            DescriptionComponent(uiState)

            Divisor()

            StatusComponent(uiState, formViewModel)

            Divisor()

            DateComponent(uiState, formViewModel)

            if ( formViewModel.isOpenCalendar.value ) {
                CalendarComponent(formViewModel)
            }
        }
    }
}

