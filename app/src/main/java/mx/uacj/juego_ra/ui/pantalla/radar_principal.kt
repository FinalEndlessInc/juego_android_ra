package mx.uacj.juego_ra.ui.pantalla

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

var modificador_radar = Modifier
    .fillMaxWidth()
    .border(3.dp, color = Color.Blue, shape = RectangleShape)
    .padding(5.dp)

@Composable
fun pantalla_radar(modificador: Modifier = Modifier){
    Column {
        Row {

        }
    }
}

@Preview
@Composable
fun previsualizar_radar(){
    pantalla_radar()
}