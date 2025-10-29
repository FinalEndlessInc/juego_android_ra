package mx.uacj.juego_ra.repositorios_estaticos

import android.location.Location
import androidx.collection.emptyObjectList
import androidx.collection.objectListOf
import mx.uacj.juego_ra.ui.modelos.Boton
import mx.uacj.juego_ra.ui.modelos.Informacion
import mx.uacj.juego_ra.ui.modelos.InformacionInteractiva
import mx.uacj.juego_ra.ui.modelos.Pista

object RepositorioPruebas{
    var pistas = listOf(Pista(
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
            ubicacion = Location("proveedor"),
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 2",
                imagen = null
            )
        ),
        Pista(
            nombre = "pista 3",
            ubicacion = Location("proveedor"),
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 3",
                imagen = null
            )
        ),
        Pista(
            nombre = "pista 4",
            ubicacion = Location("proveedor"),
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