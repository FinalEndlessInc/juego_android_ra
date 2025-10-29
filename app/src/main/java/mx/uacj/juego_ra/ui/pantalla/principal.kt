package mx.uacj.juego_ra.ui.pantalla

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.uacj.juego_ra.organismos.InformacionInteractivaVista
import mx.uacj.juego_ra.organismos.InformacionVista
import mx.uacj.juego_ra.repositorios_estaticos.RepositorioPruebas
import mx.uacj.juego_ra.ui.modelos.Informacion
import mx.uacj.juego_ra.ui.modelos.TiposDePistas

@Composable
fun Principal(modificador: Modifier = Modifier){
    val mi_ubicacion = Location("proveedor").apply{
        latitude = 31.7156044
        longitude = -106.4246012
    }
    Column {
        for(pista in RepositorioPruebas.pistas){
            Text("La pista es ${pista.ubicacion}")
            Text("La distancia a la pista es ${mi_ubicacion.distanceTo(pista.ubicacion)}")
            when(pista.cuerpo.tipo){
                TiposDePistas.texto ->{
                    InformacionVista(pista.cuerpo as Informacion)
                }
                TiposDePistas.interactiva ->{
                    InformacionInteractivaVista(pista.cuerpo)
                }
                TiposDePistas.camara ->{

                }
                TiposDePistas.agitar_telefono -> {
                    TODO()
                }
            }
            Text("--------------------")
        }
    }

}