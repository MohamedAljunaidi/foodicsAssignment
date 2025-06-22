package com.assignment.theme.extensions

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

enum class WindowSizeClass { Compact, Medium, Expanded }

@SuppressLint("ConfigurationScreenWidthHeight")
@Composable
fun getWindowSizeClass(): WindowSizeClass {
    val configuration = LocalConfiguration.current
    val screenWidthDp = configuration.screenWidthDp
    return when {
        screenWidthDp <= 360 -> WindowSizeClass.Compact
        screenWidthDp in 361..599 -> WindowSizeClass.Medium
        else -> WindowSizeClass.Expanded
    }
}