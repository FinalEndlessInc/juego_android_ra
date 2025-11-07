package mx.uacj.juego_ra.repositorios_estaticos

import android.location.Location
import androidx.collection.emptyObjectList
import androidx.collection.objectListOf
import com.google.android.gms.common.util.CollectionUtils.listOf
import mx.uacj.juego_ra.modelos.Boton
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.Pista

object RepositorioPruebas{
    var pistas = listOf(
        Pista(
        nombre = "pista 1",
        ubicacion = Location("proveedor").apply{
            latitude = 31.7156044
            longitude = -106.4246012
        },

            cuerpo = Informacion(
            texto = "Prueba de texto para comprobar esto pista 1",
            imagen = null
        )),
        Pista(
            nombre = "pista 2",
            ubicacion = Location("proveedor").apply{
                latitude = 31.742593
                longitude = -106.433514
            },

            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 2",
                imagen = null
            )
        ),
        Pista(
            nombre = "pista 3",
            ubicacion = Location("proveedor").apply{
                latitude = 31.7156044
                longitude = -106.4246012
            },

            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 3",
                imagen = null
            )
        ),
        Pista(
            nombre = "pista 4",
            ubicacion = Location("proveedor").apply{
                latitude = 31.7156044
                longitude = -106.4246012
            },

            cuerpo = InformacionInteractiva(
                texto = "Prueba de pista tipo interactiva",
                lista_de_botones = listOf(Boton(
                    texto = "IR a pantalla 1",
                    direccion = null
                ))
            )
        ),
    )
}