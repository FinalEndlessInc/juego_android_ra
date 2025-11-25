package mx.uacj.juego_ra.ui.pantalla

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.Pista
import mx.uacj.juego_ra.ui.controladores.PantallaInteraccionCorrecta
import mx.uacj.juego_ra.ui.controladores.PantallaInteraccionIncorrecta
import mx.uacj.juego_ra.ui.moleculas.BotonInteraccion
import mx.uacj.juego_ra.ui.moleculas.texto_para_boton
import mx.uacj.juego_ra.view_models.ControladorGeneral

@Composable
fun InformacionInteractivaPista(navegador: NavHostController, informacion_interactiva: InformacionInteractiva){

    val controlador_general: ControladorGeneral = hiltViewModel()
    var respuesta_seleccionada by remember { mutableStateOf(false) }

    Column {
        Text("${informacion_interactiva.texto}")

        for (boton in informacion_interactiva.lista_de_botones){
            Row{
                RadioButton(
                    selected = (respuesta_seleccionada == boton.respuesta),
                    onClick = { respuesta_seleccionada = boton.respuesta}
                )
                Text("${boton.texto}")
            }
            Row(Modifier.clickable {
                if (respuesta_seleccionada){
                    navegador.navigate(PantallaInteraccionCorrecta)
                }else{
                    navegador.navigate(PantallaInteraccionIncorrecta)
                }
            }){
                BotonInteraccion(texto_para_boton.Seleccionar)
            }
            /*Text("${boton.texto}", modifier = Modifier.clickable {
                if(boton.respuesta){
                    navegador.navigate(PantallaInteraccionCorrecta)
                }else{
                    navegador.navigate(PantallaInteraccionIncorrecta)
                }
                controlador_general.seleccionar_pista(boton.direccion)
                navegador.navigate("SelectorPantallaPista")
            })*/
        }
    }
}