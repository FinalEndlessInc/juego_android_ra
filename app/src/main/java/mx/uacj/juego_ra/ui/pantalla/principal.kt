package mx.uacj.juego_ra.ui.pantalla

import android.location.Location
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavHostController
import mx.uacj.juego_ra.organismos.InformacionVista
import mx.uacj.juego_ra.repositorios.estaticos.RepositorioPruebas
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.TiposDePistas
import mx.uacj.juego_ra.organismos.DetectorAgitamiento
import mx.uacj.juego_ra.repositorios.RepositorioInformacionGeneral
import mx.uacj.juego_ra.ui.controladores.NavegadorPrincipal
import mx.uacj.juego_ra.ui.controladores.OpcionNavegacionPantallaPistaAgitable
import mx.uacj.juego_ra.ui.controladores.OpcionNavegacionPantallaPistaCamara
import mx.uacj.juego_ra.ui.controladores.OpcionNavegacionPantallaPistaInformacion
import mx.uacj.juego_ra.ui.controladores.OpcionNavegacionPantallaPistaInteractuable
import mx.uacj.juego_ra.ui.moleculas.BotonInteraccion
import mx.uacj.juego_ra.ui.moleculas.EsferasConseguidas
import mx.uacj.juego_ra.ui.moleculas.EspacioBoton
import mx.uacj.juego_ra.ui.moleculas.Fondo
import mx.uacj.juego_ra.ui.moleculas.Margen
import mx.uacj.juego_ra.ui.moleculas.PantallaRadar
import mx.uacj.juego_ra.ui.moleculas.texto_para_boton
import mx.uacj.juego_ra.view_models.ControladorGeneral
import mx.uacj.juego_ra.view_models.GestorUbicacion

@Composable
fun llamenAlBoton(textoParaBoton: texto_para_boton){
    BotonInteraccion(textoParaBoton)
}

@Composable
fun Principal(
    navegador: NavHostController,
    modificador: Modifier = Modifier,
    gestor_ubicacion: GestorUbicacion = hiltViewModel(),
    controlador_general: ControladorGeneral = hiltViewModel()
){

    var mostrar_pantalla_generica by remember { mutableStateOf(true) }
    var mostrar_pista_cercana by remember { mutableStateOf(false) }

    //var mostrar_informacion_relacionada_con_las_agitadas by remember { mutableStateOf(false) }

    var ubicacion = gestor_ubicacion.ubicacion_actual

    val interactionSource = remember { MutableInteractionSource() }

    Box{
        Fondo()
        Column(modificador.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(horizontalArrangement = Arrangement.Center) {
                Box{
                    PantallaRadar()
                    Column(Modifier.fillMaxWidth(0.7f).padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally){
                        //Text("Ubicacion actual: ${ubicacion.value}", color = Color.Green)
                        for(pista in RepositorioPruebas.pistas){

                            if (ubicacion.value == null){
                                break;
                            }

                            var distancia_a_la_pista = ubicacion.value.distanceTo(pista.ubicacion)

                            if(distancia_a_la_pista < pista.distancia_maxima){
                                mostrar_pantalla_generica = false
                                var nivel_de_distancia = (distancia_a_la_pista * 100) / (pista.distancia_maxima - pista.distancia_minima)

                                Text("La pista es ${pista.nombre}", color = Color.Green, textAlign = TextAlign.Center)
                                //Text("El nivel de la distancia a la pista es ${nivel_de_distancia} metros", color = Color.Green, textAlign = TextAlign.Center)

                                if(nivel_de_distancia > 75){
                                    Text("Estas frio todavia", color = Color.Green)
                                }
                                else if(nivel_de_distancia > 50){
                                    Text("Te estas acercando", color = Color.Green)
                                }
                                else if(nivel_de_distancia > 25){
                                    Text("Muy cercas. ya merito", color = Color.Green)
                                }
                                /*else if (nivel_de_distancia < 20 && !mostrar_pista_cercana){
                                    Row (modifier = Modifier.fillMaxWidth()){
                                        Text("AVERSSSD")
                                    }
                                }*/

                                else if(distancia_a_la_pista < pista.distancia_minima){
                                    Row(modifier = Modifier
                                        .fillMaxWidth().clickable(
                                            interactionSource = interactionSource,
                                            indication = null
                                        ){
                                            navegador.navigate("SelectorPantallaPista")
                                            controlador_general.seleccionar_pista(pista)
                                         }
                                    ) {
                                        Text("Encontraste la pista", modifier = Modifier)
                                        Box{
                                            EspacioBoton()
                                            llamenAlBoton(texto_para_boton.Buscar)
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }



            Row{
                EsferasConseguidas()
            }

            /*DetectorAgitamiento(meta_de_agitadas = 20, al_llegar_a_la_meta = {
                mostrar_informacion_relacionada_con_las_agitadas = true
            })
            if(mostrar_informacion_relacionada_con_las_agitadas){
                Text("Ya wey, ya")
            }*/



        }

        Margen()
    }


    if (mostrar_pantalla_generica){
        Column(modificador) {
            Text("No te encuentras cerca de una pista por el momento")
            Text("Sigue explorando")

        }
        }
}

