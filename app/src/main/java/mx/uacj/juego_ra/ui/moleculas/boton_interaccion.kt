package mx.uacj.juego_ra.ui.moleculas

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.FlowColumnScopeInstance.align
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import mx.uacj.juego_ra.modelos.Pista

enum class texto_para_boton{
    Buscar,
    Siguiente,
    Seleccionar,
    Capturar

}

@Composable
fun BotonInteraccion(estado: texto_para_boton){

    //var boton_presionado = false
    var texto_del_boton by remember { mutableStateOf(estado) }

    Column (Modifier
        .width(175.dp)
        .height(100.dp)
        .background(Color.Green)
        .border(2.dp, color = Color.Black, shape = RectangleShape)
        .wrapContentSize(Alignment.Center)
        .padding(5.dp)
        ){

        Text(
            text = "${texto_del_boton}",
            Modifier.fillMaxWidth(),
            fontSize = 30.sp,
            fontFamily = fontLato,
            textAlign = TextAlign.Center
        )
    }
}

@Preview
@Composable
fun verBoton(){
    BotonInteraccion(texto_para_boton.Buscar)
}