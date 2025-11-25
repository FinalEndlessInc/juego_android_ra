package mx.uacj.juego_ra.modelos

data class Dialogo(
    val identificador: Int,
    val dialogo_texto: String
)

data class Interaccion(
    val personaje: String,
    val dialogos: List<Dialogo>,
    val esfera: Int,
)

