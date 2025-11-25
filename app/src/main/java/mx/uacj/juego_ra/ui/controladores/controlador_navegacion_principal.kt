package mx.uacj.juego_ra.ui.controladores

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import mx.uacj.juego_ra.ui.pantalla.PantallaInteraccionCorrecta
import mx.uacj.juego_ra.ui.pantalla.PantallaInteraccionIncorrecta
import mx.uacj.juego_ra.ui.pantalla.Principal
import mx.uacj.juego_ra.ui.pantalla.modificador_radar

@Composable
fun NavegadorPrincipal(modificador: Modifier = Modifier){
    var controlador_de_navegacion = rememberNavController()

    NavHost(controlador_de_navegacion, startDestination = OpcionNavegacionPantallaPrincipal){
        composable<OpcionNavegacionPantallaPrincipal> {
            Principal(controlador_de_navegacion, modificador)
        }

        composable("SelectorPantallaPista") {
            SeleccionarPantallaPista(controlador_de_navegacion, modificador)
        }

        composable<PantallaInteraccionCorrecta>{
            PantallaInteraccionIncorrecta()
        }

        composable<PantallaInteraccionIncorrecta> {
            PantallaInteraccionCorrecta()
        }

        /*composable<OpcionNavegacionPantallaPistaInteractuable> {
            SeleccionarPantallaPista(modificador)
        }

        composable<OpcionNavegacionPantallaPistaCamara> {
            SeleccionarPantallaPista(modificador)
        }

        composable<OpcionNavegacionPantallaPistaAgitable> {
            SeleccionarPantallaPista(modificador)
        }*/

    }
}