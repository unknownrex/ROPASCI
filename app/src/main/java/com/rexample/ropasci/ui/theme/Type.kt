package com.rexample.ropasci.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.rexample.ropasci.R

// Set of Material typography styles to start with
val poppins = FontFamily(
    Font(R.font.poppins_semibold, FontWeight.SemiBold),
    Font(R.font.poppins_bold, FontWeight.Bold)
)

val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 18.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp
    ),
    bodySmall = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    titleLarge = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp
    ),
    titleMedium = TextStyle(
        fontFamily = poppins,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp
    )
    /* Other default text styles to override
    titleLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)