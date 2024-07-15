package com.example.agenda.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import com.example.agenda.viewmodel.ThemeViewModel
import org.koin.androidx.compose.koinViewModel

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFFCCCCCC),
    secondary = Color(0xFF626277),
    onSecondary = SubMenuInactiveDark,
    tertiary = Color(0xFFFFFFFF),
    onTertiary = Color(0xFF97A3A5),
    background = Color(0xFF090909),
    onBackground = SubMenuBackgroundDark,
    outline = StrokeFormDark,
    outlineVariant = SubTitleDark,
    error = ButtonDeleteTaskDark,
)

private val LightColorScheme = lightColorScheme(
    primary = PrimaryLight,
    onPrimary = SubMenuActiveLight,
    secondary = ButtonInactiveLight,
    onSecondary = SubMenuInactiveLight,
    tertiary = TitleLight,
    onTertiary = ButtonInactiveLight,
    background = SecondaryLight,
    onBackground = SubMenuBackgroundLight,
    outline = StrokeFormLight,
    outlineVariant = SubTitleLight,
    error = ButtonDeleteTaskLight,
)

@Composable
fun AgendaTheme(
    //darkTheme: Boolean = isSystemInDarkTheme(),
    darkTheme: Boolean = false,
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {

    val themeViewModel = koinViewModel<ThemeViewModel>()
    val uiState by themeViewModel.uiState.collectAsState()


    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}