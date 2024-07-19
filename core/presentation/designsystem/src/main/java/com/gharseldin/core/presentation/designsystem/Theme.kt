package com.gharseldin.core.presentation.designsystem

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

val DarkColorScheme = darkColorScheme(
    primary = TaskyBlack,
    background = TaskyWhite,
    surface = TaskyDarkGray,
    secondary = TaskyWhite,
    tertiary = TaskyWhite,
    primaryContainer = TaskyGreen,
    onPrimary = TaskyWhite,
    onBackground = TaskyWhite,
    onSurface = TaskyWhite,
    onSurfaceVariant = TaskyGray,
    error = TaskyError
)


@Composable
fun TaskyTheme(
    content: @Composable () -> Unit
) {
    val colorScheme = DarkColorScheme
    val view = LocalView.current
    if(!view.isInEditMode){
        SideEffect {
            val window = (view.context as Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = false
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        content = content,
        typography = Typography
    )
}
