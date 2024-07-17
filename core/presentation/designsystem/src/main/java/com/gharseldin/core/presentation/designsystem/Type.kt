package com.gharseldin.core.presentation.designsystem

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight

val Inter = FontFamily(
    Font(
        resId = R.font.inter_thin,
        weight = FontWeight.Thin
    ),
    Font(
        resId = R.font.inter_extralight,
        weight = FontWeight.ExtraLight
    ),
    Font(
        resId = R.font.inter_light,
        weight = FontWeight.Light
    ),
    Font(
        resId = R.font.inter_regular,
        weight = FontWeight.Normal
    ),
    Font(
        resId = R.font.inter_medium,
        weight = FontWeight.Medium
    ),
    Font(
        resId = R.font.inter_semibold,
        weight = FontWeight.SemiBold
    ),
    Font(
        resId = R.font.inter_bold,
        weight = FontWeight.Bold
    ),
    Font(
        resId = R.font.inter_extrabold,
        weight = FontWeight.ExtraBold
    ),
    Font(
        resId = R.font.inter_black,
        weight = FontWeight.Black
    )
)

val Typography = Typography(
    // TODO get the exact values from Figma during implementation of the ui
)