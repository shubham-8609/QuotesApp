package com.example.quotesapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xFF60A5FA),       // Lighter blue for dark mode
    onPrimary = Color(0xFF0F172A),

    secondary = Color(0xFF94A3B8),
    onSecondary = Color(0xFF0F172A),

    tertiary = Color(0xFF38BDF8),

    background = Color(0xFF020617),    // Deep dark (not gray)
    onBackground = Color(0xFFE2E8F0),  // Light text

    surface = Color(0xFF0F172A),       // Card background
    onSurface = Color(0xFFE2E8F0)
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xFF2563EB),       // Blue (main brand)
    onPrimary = Color.White,

    secondary = Color(0xFF64748B),     // Slate gray
    onSecondary = Color.White,

    tertiary = Color(0xFF0EA5E9),      // Accent blue

    background = Color(0xFFF8FAFC),    // Soft white (better than pure white)
    onBackground = Color(0xFF0F172A),  // Dark text

    surface = Color(0xFFFFFFFF),       // Cards
    onSurface = Color(0xFF0F172A)
)

@Composable
fun QuotesAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    val view = LocalView.current

    SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = colorScheme.primary.toArgb()

        WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars =
            !darkTheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}