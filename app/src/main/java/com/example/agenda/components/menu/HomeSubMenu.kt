package com.example.agenda.components.menu

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agenda.constants.ItemsSubMenu
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.SubMenuActive
import com.example.agenda.ui.theme.SubMenuBackground
import com.example.agenda.ui.theme.SubMenuInactive

@Composable
fun HomeSubMenu(
    onClickButtonLeft: () -> Unit,
    onClickButtonCenter: () -> Unit,
    onClickButtonRight: () -> Unit,
    currentItemSubMenuActive: String,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(50))
            .border(
                border = BorderStroke(1.dp, Color(0xFFBDBDBD)),
                shape = RoundedCornerShape(50)
            )
            .background(SubMenuBackground)
    ) {
        Button(
            onClick = onClickButtonLeft,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .fillMaxWidth()
                .weight(1F),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (currentItemSubMenuActive == ItemsSubMenu.PENDING.name) Secondary else SubMenuBackground
            )
        ) {
            SwitchTextButton(text = ItemsSubMenu.PENDING.name, currentItemSubMenuActive)
        }

        Button(
            onClick = onClickButtonCenter,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .fillMaxWidth()
                .weight(1F),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (currentItemSubMenuActive == ItemsSubMenu.IN_PROGRESS.name) Secondary else SubMenuBackground
            )
        ) {
            SwitchTextButton(text = ItemsSubMenu.IN_PROGRESS.name, currentItemSubMenuActive)
        }

        Button(
            onClick = onClickButtonRight,
            modifier = Modifier
                .clip(RoundedCornerShape(50))
                .fillMaxWidth()
                .weight(1F),
            colors = ButtonDefaults.buttonColors(
                containerColor = if ((currentItemSubMenuActive == ItemsSubMenu.FINISHED.name)) Secondary else SubMenuBackground
            )
        ) {
            SwitchTextButton(text = ItemsSubMenu.FINISHED.name, currentItemSubMenuActive)
        }
    }
}

@Composable
fun SwitchTextButton(text: String, currentItemSubMenuActive: String) {
    val textColor = when (currentItemSubMenuActive) {
        ItemsSubMenu.PENDING.name -> SubMenuActive
        ItemsSubMenu.IN_PROGRESS.name -> SubMenuActive
        ItemsSubMenu.FINISHED.name -> SubMenuActive
        else -> SubMenuInactive
    }

    Text(
        text = text,
        style = TextStyle(
            color = textColor,
            fontWeight = FontWeight.W600,
            fontSize = 16.sp,
            lineHeight = 20.sp,
            textAlign = TextAlign.Center
        ),
        modifier = Modifier.padding(4.dp)
    )
}