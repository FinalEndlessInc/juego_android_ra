package mx.uacj.juego_ra.ui.pantalla

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import mx.uacj.juego_ra.ui.moleculas.BotonInteraccion
import mx.uacj.juego_ra.ui.moleculas.texto_para_boton

var modificador_radar = Modifier
    .fillMaxWidth()
    .fillMaxHeight()
    .border(3.dp, color = Color.Blue, shape = RectangleShape)
    .wrapContentSize(Alignment.Center)
    .padding(40.dp)

@Composable
fun pantalla_radar(modificador: Modifier = Modifier){
    Column(modifier = modificador_radar){
        Row(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.8f)
                .background(Color.DarkGray)

        ) {
            Text("Eaaa")
        }
        Row {
            BotonInteraccion(texto_para_boton.Buscar)
        }
    }
}

@Preview
@Composable
fun previsualizar_radar(){
    pantalla_radar()
}