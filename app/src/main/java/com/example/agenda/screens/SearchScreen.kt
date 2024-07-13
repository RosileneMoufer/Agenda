package com.example.agenda.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.agenda.components.list.Card
import com.example.agenda.components.menu.HomeSubMenu
import com.example.agenda.components.menu.TopBarSearchScreen
import com.example.agenda.state.SearchStateUiState
import com.example.agenda.state.TaskFormUiState
import com.example.agenda.state.TasksListUiState
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.viewmodel.SearchViewModel
import com.example.agenda.viewmodel.TaskFormViewModel
import com.example.agenda.viewmodel.TasksListViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.compose.koinViewModel

@Composable
fun SearchScreen(
    navController: NavController
) {
    val taskFormViewModel = koinViewModel<TaskFormViewModel>()
    val taskListViewModel = koinViewModel<TasksListViewModel>()
    val uiState by taskListViewModel.uiState.collectAsState(TasksListUiState())
    val taskFormUiState by taskFormViewModel.uiState.collectAsState(TasksListUiState())

    val searchViewModel = koinViewModel<SearchViewModel>()
    val searchUiState by searchViewModel.uiState.collectAsState(SearchStateUiState())
    //val searchUiState2 by searchViewModel.taskList.collectAsState(SearchStateUiState())

    Scaffold(
        modifier = Modifier.padding(16.dp),
        topBar = {
            TopBarSearchScreen(
                navController = navController,
                searchViewModel = searchViewModel,
                searchStateUiState = searchUiState
            )
        }
    ) { innitPadding ->
        if (searchUiState.tasks.isEmpty()) {
            WhenDontHaveNothingToShow("pendentes")
        } else {
            LazyColumn(
                Modifier
                    .padding(innitPadding)
                    .fillMaxWidth()
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) { itemsIndexed(uiState.pendingTasks) { _, task ->
                Card(task, navController)
            }}
        }
    }
}