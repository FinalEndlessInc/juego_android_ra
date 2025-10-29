package mx.uacj.juego_ra.ui.modelos

import android.location.Location

data class Pista(
    var nombre: String,
    var ubicacion: Location,
    var cuerpo: PistaGenerica
)
