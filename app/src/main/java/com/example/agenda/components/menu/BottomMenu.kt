package com.example.agenda.components.menu

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Build
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.agenda.constants.ItemsMenu
import com.example.agenda.constants.ItemsMenuName
import com.example.agenda.viewmodel.BottomMenuViewModel

class ItemNavigationBar(val nome:String, val icon: ImageVector, val action: ()->Unit)

@Composable
fun BottomMenu(bottomViewModel : BottomMenuViewModel, navController : NavController) {

    val items = listOf(
        ItemNavigationBar(ItemsMenuName.HOME.name, Icons.Filled.Home) { navController.navigate(ItemsMenu.HOME.name) },
        ItemNavigationBar(ItemsMenuName.CRIAR.name, Icons.Filled.AccountCircle) { navController.navigate(ItemsMenu.NEW_TASK.name) },
        ItemNavigationBar(ItemsMenuName.ALTERAR.name, Icons.Filled.Build) { navController.navigate(ItemsMenu.UPDATE_TASK.name) },
        ItemNavigationBar(ItemsMenuName.DELETAR.name, Icons.Filled.Build) { navController.navigate(ItemsMenu.DELETE_TASK.name) }
    )

    NavigationBar(containerColor = Color.White, contentColor = Color(0xFF5DB075)) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.nome) },
                label = { Text(item.nome) },
                selected = bottomViewModel.itemSelected.value == index,
                onClick = { if (item.nome != "Suporte") {
                    bottomViewModel.itemSelected.value = index
                }; item.action() },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color(0xFF5DB075),
                    selectedTextColor = Color(0xFF5DB075),
                    indicatorColor = Color(0xFFEFFFF3),
                ),
            )
        }
    }
}