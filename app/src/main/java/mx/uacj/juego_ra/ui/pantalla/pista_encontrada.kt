package mx.uacj.juego_ra.ui.pantalla

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.Interaccion
import mx.uacj.juego_ra.modelos.Pista
import mx.uacj.juego_ra.modelos.TiposDePistas
import mx.uacj.juego_ra.ui.moleculas.BotonInteraccion
import mx.uacj.juego_ra.ui.moleculas.mostrar_interaccion
import mx.uacj.juego_ra.ui.moleculas.texto_para_boton

fun pista_interactiva(pista: InformacionInteractiva):Boolean{
    return false
}

@Composable
fun PistaActual(pista: Pista){
    Column {
        Row {
            val modificador: Modifier = Modifier
            mostrar_interaccion(modificador, pista.interaccion)

        }

        Row {
            BotonInteraccion(texto_para_boton.Siguiente)
        }
    }


}