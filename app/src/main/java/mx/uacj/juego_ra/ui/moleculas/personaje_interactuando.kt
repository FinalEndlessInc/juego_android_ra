package mx.uacj.juego_ra.ui.moleculas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.uacj.juego_ra.R
import mx.uacj.juego_ra.modelos.Interaccion
import mx.uacj.juego_ra.modelos.Pista

@Composable
fun mostrar_dialogo(dialogos: Interaccion, i: Int):String{

    return dialogos.dialogos[i].toString()
}

@Composable
fun mostrar_interaccion(modificador : Modifier = Modifier, personaje : Interaccion){
     var q: Int = 0
     var dialogo by remember { mutableStateOf(null) }

    Row(modificador) {
        Box(modificador
                .fillMaxWidth()
                .background(color = Color.Blue)
        ){
            Column {
                Row {
                    Image(
                        painter = painterResource(R.drawable.goku),
                        contentDescription = "Imagen de goku",
                        modifier = Modifier.height(600.dp)
                    )
                }
                Row {
                    for( i in personaje.dialogos){
                        Column(
                            modifier = Modifier
                                .offset(x = 75.dp, y = 430.dp)
                                .size(width = 300.dp, height = 150.dp)
                                .background(color = Color.White)
                                .border(5.dp,Color.LightGray ,shape = RectangleShape)
                                .padding(15.dp)
                        ){
                            dialogo = mostrar_dialogo(personaje, q) // Arreglar despues
                        }

                        /*if(sePushoBoton()) {
                            q++
                        }*/
                    }

                }

            }

        }
    }
}

/*@Preview
@Composable
fun previsualizacion_de_personaje(){
    mostrar_interaccion(personaje)
}*/