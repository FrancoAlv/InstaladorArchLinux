package com.linux.createcompilador.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.platform.Font


val regular = FontWeight(750)

private val poppins = FontFamily(
    Font("fonts/poppins_black.ttf", FontWeight.Black),
    Font("fonts/poppins_bold.ttf", FontWeight.Bold),
    Font("fonts/poppins_light.ttf", FontWeight.Light),
    Font("fonts/poppins_thin.ttf", FontWeight.Thin),
    Font("fonts/poppins_extralight.ttf", FontWeight.ExtraLight),
    Font("fonts/poppins_extrabold.ttf", FontWeight.ExtraBold),
    Font("fonts/poppins_medium.ttf", FontWeight.Medium),
    Font("fonts/poppins_semibold.ttf", FontWeight.SemiBold),
    Font("fonts/poppins_regular.ttf", regular) ,
    Font("fonts/poppins_regular.ttf"),
)


fun FontFamily.Companion.POPPINS(): FontFamily {
    return poppins
}
