package com.example.agenda.components.menu

import android.content.ClipData.Item
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.state.SearchStateUiState
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.state.TasksListUiState
import com.example.agenda.ui.theme.Primary
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.StrokeForm
import com.example.agenda.ui.theme.Title
import com.example.agenda.viewmodel.SearchViewModel
import com.example.agenda.viewmodel.TaskFormViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(title: String, titleColor: Color, navController: NavController) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(bottom = 20.dp),
        colors = TopAppBarDefaults.topAppBarColors(Secondary),
        navigationIcon = {
            Icon(imageVector = Icons.Filled.Home, contentDescription = "Dark Mode", tint = Primary)
        },
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = titleColor,
                    fontWeight = FontWeight.W700,
                    fontSize = 22.sp,
                )
            )
        },
        actions = {
            IconButton(onClick = { navController.navigate(ItemsMenu.SEARCH.name) }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "search",
                    tint = Primary
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarNewTask(
    title: String,
    titleColor: Color,
    navController: NavController,
    formViewModel: TaskFormViewModel
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(bottom = 20.dp),
        colors = TopAppBarDefaults.topAppBarColors(Secondary),
        navigationIcon = {
            ActionTextButton("Cancelar") { navController.popBackStack() }
        },
        title = {
            Text(
                text = title,
                style = TextStyle(
                    color = titleColor,
                    fontWeight = FontWeight.W700,
                    fontSize = 22.sp,
                )
            )
        },
        actions = {
            ActionTextButton("Limpar") {
                formViewModel.cleanForm()
            }
        }
    )
}

@Composable
fun ActionTextButton(textButton: String, onClick: () -> Unit) {
    Text(
        modifier = Modifier.clickable { onClick() },
        text = textButton,
        style = TextStyle(
            color = Primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.W700
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarSearchScreen(
    navController: NavController,
    searchViewModel : SearchViewModel,
    searchStateUiState : SearchStateUiState
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(bottom = 20.dp),
        colors = TopAppBarDefaults.topAppBarColors(Secondary),
        navigationIcon = {
            IconButton(
                onClick = {  navController.popBackStack() },
                ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    tint = Primary
                )
            }
        },
        title = {
            TextField(
                value = "uiState.title",
                onValueChange = searchStateUiState.onSearchChange,
                label = {
                    Text(
                        text = "Título",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = Secondary,
                    unfocusedBorderColor = StrokeForm,
                    unfocusedTextColor = Title,
                    focusedContainerColor = Secondary,
                    focusedBorderColor = StrokeForm,
                    focusedLabelColor = Title,
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        actions = {
            IconButton(onClick = {
                searchViewModel.searchTasks("nova descrição")
                println("poaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Primary
                )
            }
        }
    )
}