package mx.uacj.juego_ra.repositorios.estaticos

import android.location.Location
import com.google.android.gms.common.util.CollectionUtils.listOf
import mx.uacj.juego_ra.modelos.Boton
import mx.uacj.juego_ra.modelos.Informacion
import mx.uacj.juego_ra.modelos.InformacionInteractiva
import mx.uacj.juego_ra.modelos.Pista
import mx.uacj.juego_ra.repositorios.estaticos.Interacciones.Personaje

object RepositorioPruebas{
    var pistas = listOf(
        Pista(
        nombre = "pista 1", //Edificio A
        ubicacion = Location("proveedor").apply{
           // latitude = 31.742102
           // longitude = -106.433054

            latitude = 31.715930
            longitude= -106.427743
        },
            interaccion = Personaje[0],
            cuerpo = InformacionInteractiva(
                texto = "Prueba de texto para comprobar esto pista 1",

                lista_de_botones = listOf(
                    Boton(
                    texto = "Respuesta mala",
                    direccion = "",
                    respuesta = false
                    ),
                    Boton(
                        texto = "Respuesta wena",
                        direccion = "",
                        respuesta = true
                    ),
                    Boton(
                        texto = "Respuesta mala",
                        direccion = "",
                        respuesta = false
                    ),
                )
            )
        ),
        Pista(
            nombre = "pista 2", //G1
            ubicacion = Location("proveedor").apply{
                latitude = 31.741431
                longitude = -106.432029
            },

            interaccion = Personaje[1],
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 2",
                imagen = null
            )
        ),
        Pista(
            nombre = "pista 3", //Cafetería
            ubicacion = Location("proveedor").apply{
                latitude = 31.742989
                longitude = -106.432488
            },

            interaccion = Personaje[2],
            cuerpo = Informacion(
                texto = "Prueba de texto para comprobar esto pista 3",
                imagen = null
            )
        ),
        Pista(
            nombre = "pista 4", //Biblioteca
            ubicacion = Location("proveedor").apply{
                latitude = 31.743016
                longitude = -106.432963
            },

            interaccion = Personaje[3],
            cuerpo = InformacionInteractiva(
                texto = "Prueba de pista tipo interactiva",
                lista_de_botones = listOf(Boton(
                    texto = "IR a pantalla 1",
                    direccion = "",
                    respuesta = false
                ))
            )
        ),
        Pista(
            nombre = "pista 5", //Audiovisual D
            ubicacion = Location("proveedor").apply{
                latitude = 31.743370
                longitude = -106.432528
            },

            interaccion = Personaje[4],
            cuerpo = InformacionInteractiva(
                texto = "Prueba de pista tipo interactiva",
                lista_de_botones = listOf(Boton(
                    texto = "IR a pantalla 1",
                    direccion = "",
                    respuesta = false
                ))
            )
        ),
        Pista(
            nombre = "pista 6", //Edificio W
            ubicacion = Location("proveedor").apply{
                latitude = 31.743404
                longitude = -106.432271
            },

            interaccion = Personaje[5],
            cuerpo = InformacionInteractiva(
                texto = "Prueba de pista tipo interactiva",
                lista_de_botones = listOf(Boton(
                    texto = "IR a pantalla 1",
                    direccion = "",
                    respuesta = false
                ))
            )
        ),
        Pista(
            nombre = "pista 7", //Ultimo, Y4
            ubicacion = Location("proveedor").apply{
                latitude = 31.743391
                longitude = -106.431139
            },

            interaccion = Personaje[6],
            cuerpo = InformacionInteractiva(
                texto = "Prueba de pista tipo interactiva",
                lista_de_botones = listOf(Boton(
                    texto = "IR a pantalla 1",
                    direccion = "",
                    respuesta = false
                ))
            )
        ),
    )
}