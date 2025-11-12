package mx.uacj.juego_ra.view_models

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.uacj.juego_ra.modelos.Pista
import mx.uacj.juego_ra.repositorios.RepositorioInformacionGeneral
import javax.inject.Inject

@HiltViewModel
class ControladorGeneral @Inject constructor(
    private val informacion_general: RepositorioInformacionGeneral
) : ViewModel(){
    fun el_usuario_ha_encontrado_la_pista(pista: Pista){
        informacion_general.pistas_identificadas.value
    }
}