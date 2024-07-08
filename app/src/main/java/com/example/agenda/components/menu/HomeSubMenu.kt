package com.example.agenda.components.menu

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.agenda.constants.ItemsSubMenu
import com.example.agenda.constants.itemsSubMenu
import com.example.agenda.ui.theme.Secondary
import com.example.agenda.ui.theme.SubMenuActive
import com.example.agenda.ui.theme.SubMenuBackground
import com.example.agenda.ui.theme.SubMenuInactive

@Composable
fun HomeSubMenu(
    onClickButtonLeft: () -> Unit,
    onClickButtonCenter: () -> Unit,
    onClickButtonRight: () -> Unit,
    currentItemSubMenuActive: Int,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .clip(RoundedCornerShape(40))
            .background(SubMenuBackground)
            .padding(horizontal = 4.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = onClickButtonLeft,
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .fillMaxWidth()
                .weight(1F),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (currentItemSubMenuActive == ItemsSubMenu.PENDING.ordinal) Secondary else SubMenuBackground
            )
        ) {
            Text(
                text = itemsSubMenu[0],
                style = TextStyle(
                    color = if (currentItemSubMenuActive == ItemsSubMenu.PENDING.ordinal) SubMenuActive else SubMenuInactive,
                    fontWeight = FontWeight.W600,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(1.dp)
            )
        }

        Button(
            onClick = onClickButtonCenter,
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .fillMaxWidth()
                .weight(1F),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (currentItemSubMenuActive == ItemsSubMenu.IN_PROGRESS.ordinal) Secondary else SubMenuBackground
            )
        ) {
            Text(
                text = itemsSubMenu[1],
                style = TextStyle(
                    color = if (currentItemSubMenuActive == ItemsSubMenu.IN_PROGRESS.ordinal) SubMenuActive else SubMenuInactive,
                    fontWeight = FontWeight.W600,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(1.dp)
            )
        }

        Button(
            onClick = onClickButtonRight,
            modifier = Modifier
                .clip(RoundedCornerShape(20))
                .fillMaxWidth()
                .weight(1F),
            colors = ButtonDefaults.buttonColors(
                containerColor = if ((currentItemSubMenuActive == ItemsSubMenu.FINISHED.ordinal)) Secondary else SubMenuBackground
            )
        ) {
            Text(
                text = itemsSubMenu[2],
                style = TextStyle(
                    color = if (currentItemSubMenuActive == ItemsSubMenu.FINISHED.ordinal) SubMenuActive else SubMenuInactive,
                    fontWeight = FontWeight.W600,
                    fontSize = 12.sp,
                    textAlign = TextAlign.Center
                ),
                modifier = Modifier.padding(1.dp)
            )
        }
    }
}