package mx.uacj.juego_ra

import android.annotation.SuppressLint
import android.Manifest
import android.content.pm.PackageManager

import android.location.Location

import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.util.Pair
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.EntryPoint
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import mx.uacj.juego_ra.gestor_permisos.ParaLaSolicitudDePermisos
import mx.uacj.juego_ra.ui.controladores.NavegadorPrincipal
import mx.uacj.juego_ra.ui.pantalla.Principal
import mx.uacj.juego_ra.ui.theme.Juego_raTheme
import mx.uacj.juego_ra.view_models.GestorUbicacion
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var conexion_para_obtener_ubicacion: FusedLocationProviderClient
    private lateinit var  puente_para_recibir_update_aplicacion: LocationCallback

    private var ubicacion_actual = Location("juego_ra")


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Juego_raTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    var texto_de_ubicacion by remember { mutableStateOf("No tengo permisos para ver tu ") }
                    var mostrar_resultado_de_los_permisos by remember { mutableStateOf(false) }
                    var texto_permisos_obtenidos by remember { mutableStateOf("Todos los permisos obtenidos") }

                    var gestor_ubicacion: GestorUbicacion = hiltViewModel()

                    ParaLaSolicitudDePermisos(
                        con_permisos_obtenidos = {
                            mostrar_resultado_de_los_permisos = true;

                            obtener_ubicacion_del_usuario(
                                cuando_obtenga_la_ultima_posicion_correcta = { ubicacion_nueva ->
                                    Log.w(
                                        "Ubicacion nueva",
                                        "la ubicacion nueva es ${ubicacion_nueva}"
                                    )
                                    gestor_ubicacion.actualizar_ubicacion_actual(ubicacion_nueva)
                                }
                            )
                        },
                        sin_permisos_obtenidos = {
                            mostrar_resultado_de_los_permisos = true
                            texto_permisos_obtenidos = "NO tengo los permisos necesarios para funcionar"
                        }
                    ) { }


                    Box{
                        Image(
                            painter = painterResource(R.drawable.goku),
                            contentDescription = "goku",
                            contentScale = ContentScale.FillHeight,
                            modifier = Modifier.size(150.dp)
                        )
                        NavegadorPrincipal(modificador = Modifier.padding(innerPadding))
                    }

                }
            }
        }
    }

    fun actualizar_ubicacion(ubicacion:Location){
        Log.wtf("UBICACION", "Ubicacion actual: ${ubicacion}")
        ubicacion_actual = ubicacion
    }


    @SuppressLint("MissingPermission")
    fun obtener_ubicacion_del_usuario(
        cuando_obtenga_la_ultima_posicion_correcta: (Location) -> Unit,
    ){
        conexion_para_obtener_ubicacion = LocationServices.getFusedLocationProviderClient(this)

        puente_para_recibir_update_aplicacion = object: LocationCallback(){
            override fun onLocationResult(ubicaciones: LocationResult) {
                for(ubicacion in ubicaciones.locations){
                    actualizar_ubicacion(ubicacion)
                }
            }
        }

        if(tenemos_los_permisos_de_ubicacion()){
            val constructor_del_puente_para_la_ubicacion = LocationRequest
                .Builder(TimeUnit.SECONDS.toMillis(5))
                .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
                .build()

            constructor_del_puente_para_la_ubicacion.priority = Priority.PRIORITY_HIGH_ACCURACY

            conexion_para_obtener_ubicacion.requestLocationUpdates(
                constructor_del_puente_para_la_ubicacion,
                puente_para_recibir_update_aplicacion,
                Looper.getMainLooper()
            )

            /*conexion_para_obtener_ubicacion.getCurrentLocation(
                Priority.PRIORITY_HIGH_ACCURACY,
                CancellationTokenSource().token
            )
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
                }*/
        }
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
                precision, CancellationTokenSource().token
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

    private fun tenemos_los_permisos_de_ubicacion():Boolean{
        return (ActivityCompat.checkSelfPermission(
            this, Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    this, Manifest.permission.ACCESS_COARSE_LOCATION
                )== PackageManager.PERMISSION_GRANTED
                )
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Juego_raTheme {
    }
}