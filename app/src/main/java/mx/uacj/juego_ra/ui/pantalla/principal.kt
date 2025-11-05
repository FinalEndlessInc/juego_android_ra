package mx.uacj.juego_ra.ui.pantalla

import android.location.Location
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import mx.uacj.juego_ra.organismos.InformacionInteractivaVista
import mx.uacj.juego_ra.organismos.InformacionVista
import mx.uacj.juego_ra.repositorios_estaticos.RepositorioPruebas
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.TiposDePistas

@Composable
fun Principal(ubicacion: Location?, modificador: Modifier = Modifier){
    Column {
        for(pista in RepositorioPruebas.pistas){

            if (ubicacion == null){
                break;
            }
            if(ubicacion.distanceTo(pista.ubicacion) < pista.distancia_maxima){
                Text("La pista es ${pista.ubicacion}")
                Text("La distancia a la pista es ${ubicacion?.distanceTo(pista.ubicacion)}")
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
            Text("--------------------")
        }
    }

}