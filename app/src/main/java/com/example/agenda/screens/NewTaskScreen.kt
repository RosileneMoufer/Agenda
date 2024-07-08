package com.example.agenda.screens

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.agenda.components.button.ActionButton
import com.example.agenda.components.menu.TopBarHome
import com.example.agenda.components.menu.TopBarNewTask
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.StrokeForm
import com.example.agenda.ui.theme.Title
import com.example.agenda.viewmodel.FormViewModel

@Composable
fun NewTaskScreen(navController: NavController) {
    val formViewModel = viewModel<FormViewModel>()

    Scaffold(
        modifier = Modifier
            .background(Secondary)
            .padding(16.dp),
        topBar = {
            TopBarNewTask(
                title = "Criar Task",
                titleColor = Title,
                navController
            )
        },
        bottomBar = {
            ActionButton("Criar", Primary, Secondary) {
                navController.navigate(ItemsMenu.NEW_TASK.name)
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
                value = formViewModel.title.value,
                onValueChange = {formViewModel.title.value = it},
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
                    unfocusedTextColor = Secondary,
                    focusedContainerColor = Secondary,
                    focusedBorderColor = StrokeForm,
                    focusedLabelColor = Title,
                ),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.fillMaxWidth().background(StrokeForm).height(1.dp))
        }
    }
}