package mx.uacj.juego_ra.ui.controladores

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.TiposDePistas
import mx.uacj.juego_ra.organismos.InformacionInteractivaVista
import mx.uacj.juego_ra.organismos.InformacionVista

@Composable
fun seleccionarPantallaPista(modificador: Modifier = Modifier){
    if (mostrar_pista_cercana){
        when(pista.cuerpo.tipo){
            TiposDePistas.texto ->{
                InformacionVista(pista.cuerpo as Informacion)
            }
            TiposDePistas.interactiva ->{
                InformacionInteractivaVista(pista.cuerpo as InformacionInteractiva)
            }
            TiposDePistas.camara ->{

            }
            TiposDePistas.agitar_telefono -> {

            }
        }
    }
}