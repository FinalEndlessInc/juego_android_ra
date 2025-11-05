package mx.uacj.juego_ra

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Pair
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.EntryPoint
import mx.uacj.juego_ra.gestor_permisos.ParaLaSolicitudDePermisos
import mx.uacj.juego_ra.ui.pantalla.Principal
import mx.uacj.juego_ra.ui.theme.Juego_raTheme

@EntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var conexion_para_obtener_ubicacion: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Juego_raTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    var texto_de_ubicacion by remember { mutableStateOf("No tengo permisos para ver tu ") }
                    var mostrar_resultado_de_los_permisos by remember { mutableStateOf(false) }
                    var texto_permisos_obtenidos by remember { mutableStateOf("Todos los permisos obtenidos") }

                    ParaLaSolicitudDePermisos(
                        con_permisos_obtenidos = {
                            mostrar_resultado_de_los_permisos = true;

                            obtener_ubicacion_del_usuario()
                        },
                        sin_permisos_obtenidos = {
                            mostrar_resultado_de_los_permisos = true
                            texto_permisos_obtenidos = "NO tengo los permisos necesarios para funcionar"
                        }
                    ) { }
                }
            }
        }
    }
    @SuppressLint("MissingPermission")
    fun obtener_ubicacion_del_usuario(
        cuando_obtenga_la_ultima_posicion_correcta: (Pair<Double, Double>) -> Unit,
        cuando_falle_al_obtener_ubicacion: (Exception) -> Unit,
        cuando_la_ultima_posicion_sea_nula: () -> Unit
    ){
        conexion_para_obtener_ubicacion = LocationServices.getFusedLocationProviderClient(this)

        if(tenemos_los_permisos_de_ubicacion()){
            conexion_para_obtener_ubicacion.lastLocation
                .addOnSuccessListener { ubicacion ->
                    if(ubicacion != null){
                        cuando_obtenga_la_ultima_posicion_correcta(Pair(ubicacion.latitude,ubicacion.longitude))
                    }
                    else{
                        cuando_la_ultima_posicion_sea_nula()
                    }
                }
                .addOnFailureListener{ error ->
                    cuando_falle_al_obtener_ubicacion(error)
                }
        }
    }

    private fun tengo_los_permisos_necesarios():Boolean{
        return (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCES_COARSE_LOCATION
                )== PackageManager.PERMISSION_GRANTED
                )
    }

    @SuppressLint("MissingPermission")
    fun obtener_ubicacion(
        al_obtener_la_ubicacion: (Pair<Double, Double>) -> Unit,
        al_obtener_un_error: (Exception) -> Unit,
        prioridad: Boolean = true
    ){
        val precision = if(prioridad) Priority.PRIORITY_HIGH_ACCURACY else Priority.PRIORITY_BALANCED_POWER_ACCURACY

        if(tenemos_los_permisos_de_ubicacion()){
            conexion_para_obtener_ubicacion.getCurrentLocation(
                precision, CancellationTokenSource().taken
            ).addOnSuccessListener{ ubicacion ->
                if(ubicacion != null) {
                    al_obtener_la_ubicacion(Pair(ubicacion.latitude, ubicacion.longitude))
                }
            }
                .addOnFailureListener{ error ->
                    al_obtener_un_error(error)
                }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Juego_raTheme {
    }
}