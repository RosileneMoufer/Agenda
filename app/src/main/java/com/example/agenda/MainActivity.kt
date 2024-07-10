package com.example.agenda

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.agenda.backend.data.Task
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.screens.DeleteTaskScreen
import com.example.agenda.screens.HomeScreen
import com.example.agenda.screens.NewTaskScreen
import com.example.agenda.screens.UpdateTaskScreen
import com.example.agenda.ui.theme.AgendaTheme

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
            HomeScreen(
                navController
            )
        }
        composable(ItemsMenu.NEW_TASK.name) {
            NewTaskScreen(
                navController
            )
        }
        composable(ItemsMenu.UPDATE_TASK.name) {
            UpdateTaskScreen(
                Task(id = 123,
                    title = "Editando",
                    description = "ijfeiw jfiejwi",
                    status = "Pendente",
                    hour = "11:20"), navController
            )
        }
        composable(ItemsMenu.DELETE_TASK.name) {
            DeleteTaskScreen(
                navController
            )
        }
    }
}