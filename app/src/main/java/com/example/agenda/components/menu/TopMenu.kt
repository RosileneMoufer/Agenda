package com.example.agenda.components.menu

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Bedtime
import androidx.compose.material.icons.filled.BedtimeOff
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import com.example.agenda.state.SearchUiState
import com.example.agenda.state.ThemeUiState
import com.example.agenda.viewmodel.SearchViewModel
import com.example.agenda.viewmodel.TaskFormViewModel
import com.example.agenda.viewmodel.ThemeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarHome(title: String,
               titleColor: Color,
               themeViewModel: ThemeViewModel,
               themeUiState: ThemeUiState,
               navController: NavController) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(bottom = 20.dp),
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        navigationIcon = {
            IconButton(onClick = { themeViewModel.changeThemeOfApp(!themeUiState.isDarkTheme)
            }) {
                Icon(imageVector = if (themeUiState.isDarkTheme)
                    Icons.Filled.BedtimeOff else Icons.Filled.Bedtime,
                    contentDescription = "Dark Mode",
                    tint = MaterialTheme.colorScheme.primary)
            }
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
                    tint = MaterialTheme.colorScheme.primary
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
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
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
            color = MaterialTheme.colorScheme.primary,
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
    searchStateUiState : SearchUiState
) {
    CenterAlignedTopAppBar(
        modifier = Modifier.padding(bottom = 20.dp),
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.background),
        navigationIcon = {
            IconButton(
                onClick = {  navController.popBackStack() },
                ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    contentDescription = "Voltar",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        },
        title = {
            TextField(
                value = searchStateUiState.search,
                onValueChange = searchStateUiState.onSearchChange,
                label = {
                    Text(
                        text = "Título",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.W500
                    )
                },
                colors = OutlinedTextFieldDefaults.colors(
                    unfocusedContainerColor = MaterialTheme.colorScheme.background,
                    unfocusedBorderColor = MaterialTheme.colorScheme.outline,
                    unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
                    focusedContainerColor = MaterialTheme.colorScheme.background,
                    focusedBorderColor = MaterialTheme.colorScheme.outline,
                    focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                ),
                modifier = Modifier.fillMaxWidth()
            )
        },
        actions = {
            IconButton(onClick = {
                searchViewModel.searchTasks(searchStateUiState.search)
            }) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = MaterialTheme.colorScheme.primary
                )
            }
        }
    )
}