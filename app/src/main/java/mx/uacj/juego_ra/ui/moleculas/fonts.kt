package mx.uacj.juego_ra.ui.moleculas

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import mx.uacj.juego_ra.gestor_permisos.provider

val _Lato = GoogleFont("Lato")

val fontLato = FontFamily(
    Font(googleFont = _Lato, fontProvider = provider)
)