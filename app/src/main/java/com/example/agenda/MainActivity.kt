package com.example.agenda

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
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.screens.DeleteTaskScreen
import com.example.agenda.screens.HomeScreen
import com.example.agenda.screens.NewTaskScreen
import com.example.agenda.screens.UpdateTaskScreen
import com.example.agenda.ui.theme.AgendaTheme
import com.example.agenda.viewmodel.BottomMenuViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AgendaTheme {
                val navController = rememberNavController()
                val bottomMenuViewModel = viewModel<BottomMenuViewModel>()

                NavBottomBarController(bottomMenuViewModel, navController)
            }
        }
    }
}

@Composable
fun NavBottomBarController(
    bottomMenuViewModel: BottomMenuViewModel,
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = ItemsMenu.HOME.name) {
        composable(ItemsMenu.HOME.name) {
            HomeScreen(
                bottomMenuViewModel,
                navController
            )
        }
        composable(ItemsMenu.NEW_TASK.name) {
            NewTaskScreen(
                bottomMenuViewModel,
                navController
            )
        }
        composable(ItemsMenu.UPDATE_TASK.name) {
            UpdateTaskScreen(
                bottomMenuViewModel,
                navController
            )
        }
        composable(ItemsMenu.DELETE_TASK.name) {
            DeleteTaskScreen(
                bottomMenuViewModel,
                navController
            )
        }
    }
}