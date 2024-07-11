package com.example.agenda

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agenda.backend.entity.TaskEntity
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.model.TaskModel
import com.example.agenda.screens.DeleteTaskScreen
import com.example.agenda.screens.HomeScreen
import com.example.agenda.screens.NewTaskScreen
import com.example.agenda.screens.UpdateTaskScreen
import com.example.agenda.state.TasksListUiState
import com.example.agenda.ui.theme.AgendaTheme
import com.example.agenda.viewmodel.FormViewModel
import com.example.agenda.viewmodel.TasksListViewModel
import org.koin.androidx.compose.koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        enableEdgeToEdge()
        setContent {
            AgendaTheme {
                val navController = rememberNavController()

                NavBottomBarController(navController)
            }
        }
    }
}

@Composable
fun NavBottomBarController(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ItemsMenu.HOME.name) {
        composable(ItemsMenu.HOME.name) {
            val viewModel = koinViewModel<TasksListViewModel>()
            val uiState by viewModel.uiState.collectAsState(TasksListUiState())

            HomeScreen(
                navController,
                uiState
            )
        }
        composable(ItemsMenu.NEW_TASK.name) {
            val viewModel = koinViewModel<FormViewModel>()
            val uiState by viewModel.uiState.collectAsState()

            NewTaskScreen(
                navController,
                viewModel,
                uiState
            )
        }
        composable(ItemsMenu.UPDATE_TASK.name) {
            val results =
                navController.previousBackStackEntry?.savedStateHandle?.get<TaskModel>("task")
            if (results != null) {
                UpdateTaskScreen(
                    results, navController
                )
            } else {
                // exibe tela "Not found"
            }


        }
        composable(ItemsMenu.DELETE_TASK.name) {
            DeleteTaskScreen(
                navController
            )
        }
    }
}