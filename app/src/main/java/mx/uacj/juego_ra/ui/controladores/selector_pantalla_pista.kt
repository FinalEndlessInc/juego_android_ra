package mx.uacj.juego_ra.ui.controladores

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.TiposDePistas
import mx.uacj.juego_ra.organismos.InformacionVista
import mx.uacj.juego_ra.ui.moleculas.mostrar_interaccion
import mx.uacj.juego_ra.ui.pantalla.InformacionInteractivaPista
import mx.uacj.juego_ra.view_models.ControladorGeneral

@Composable
fun SeleccionarPantallaPista(navegador: NavHostController, modificador: Modifier = Modifier, controlador_general: ControladorGeneral = hiltViewModel()){
    val pista_actual by controlador_general.pista_actual


        when(pista_actual!!.cuerpo.tipo){
            TiposDePistas.texto ->{
                InformacionVista(pista_actual!!.cuerpo as Informacion)
            }
            TiposDePistas.interactiva ->{
                InformacionInteractivaPista(navegador = navegador,pista_actual!!.cuerpo as InformacionInteractiva)
            }
            TiposDePistas.camara ->{
                TODO()
            }
            TiposDePistas.agitar_telefono -> {
                TODO()
            }
        }
}