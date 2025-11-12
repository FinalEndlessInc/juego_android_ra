package mx.uacj.juego_ra.ui.pantalla

import android.location.Location
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import mx.uacj.juego_ra.organismos.InformacionInteractivaVista
import mx.uacj.juego_ra.organismos.InformacionVista
import mx.uacj.juego_ra.repositorios.estaticos.RepositorioPruebas
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.TiposDePistas
import mx.uacj.juego_ra.organismos.DetectorAgitamiento
import mx.uacj.juego_ra.view_models.GestorUbicacion

@Composable
fun Principal( modificador: Modifier = Modifier, gestor_ubicacion: GestorUbicacion = hiltViewModel()){

    var mostrar_pantalla_generica by remember { mutableStateOf(true) }
    var mostrar_pista_cercana by remember { mutableStateOf(false) }

    var mostrar_informacion_relacionada_con_las_agitadas by remember { mutableStateOf(false) }

    var ubicacion = gestor_ubicacion.ubicacion_actual

    Column {
        DetectorAgitamiento(meta_de_agitadas = 20, al_llegar_a_la_meta = {
            mostrar_informacion_relacionada_con_las_agitadas = true
        })
        if(mostrar_informacion_relacionada_con_las_agitadas){
            Text("Ya wey, ya")
        }

        for(pista in RepositorioPruebas.pistas){

            if (ubicacion.value == null){
                break;
            }

            var distancia_a_la_pista = ubicacion.value.distanceTo(pista.ubicacion)

            if(distancia_a_la_pista < pista.distancia_maxima){
                mostrar_pantalla_generica = false
                var nivel_de_distancia = (distancia_a_la_pista * 100) / (pista.distancia_maxima - pista.distancia_minima)

                Text("La pista es ${pista.nombre}")
                Text("El nivel de la distancia a la pista es ${nivel_de_distancia} metros")

                if(nivel_de_distancia > 75){
                    Text("Estas frio todavia")
                }
                else if(nivel_de_distancia > 50){
                    Text("Te estas acercando")
                }
                else if(nivel_de_distancia > 25){
                    Text("Muy cercas. ya merito")
                }
                else if (nivel_de_distancia < 20 && !mostrar_pista_cercana){
                    Row (modifier = Modifier.fillMaxWidth().clickable {
                        mostrar_pista_cercana = true
                    }){
                        Text("Capturar pista cercana")
                    }
                }
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


        }

    }

    if (mostrar_pantalla_generica){
        Column(modificador) {
            Text("No te encuentras cerca de una pista por el momento")
            Text("Sigue explorando")

        }
        }
}