package mx.uacj.juego_ra.gestor_permisos

import android.Manifest
import androidx.collection.emptyObjectList
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.google.android.gms.common.util.CollectionUtils.listOf

@Composable
fun ParaLaSolicitudDePermisos(
        con_permisos_obtenidos: () -> Unit,
        sin_permisos_obtenidos: () -> Unit,
        con_permisos_revocados: () -> Unit
){
    val estado_de_los_permisos = rememberMultiplePermissionsState(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION
        )
    ){ lista_permisos ->
        var tengo_todo_los_permisos: Boolean = false // Variable bandera o flag

        for(permiso in lista_permisos.values){
            if(!permiso){
                tengo_todo_los_permisos = false
                break
            }else{
                tengo_todo_los_permisos = true
            }
        }

    }
    LaunchedEffect(key1 = estado_de_los_permisos) {
        val tengo_los_permisos_revocados = estado_de_los_permisos.revokedPermissions.size == estado_de_los_permisos.permissions.size

        var lista_de_permisos_por_pedir: List<PermissionState> = emptyObjectList<PermissionState>()

        for(oermiso in estado_de_los_permisos.permissions){
            if(permiso.status.isGranted){
                lista_de_permisos_por_pedir.append(permiso)
            }
        }

        if(!lista_de_permisos_por_pedir.isEmpty()){
            estado_de_los_permisos.launchMultiplePermissionRequest()
        }

        if(tengo_los_permisos_revocados){
            con_permisos_revocados()
        }else{
            if(estado_de_los_permisos.allPermissionsGranted){
                con_permisos_obtenidos()
            }
            else{
                sin_permisos_obtenidos()
            }
        }
    }

}