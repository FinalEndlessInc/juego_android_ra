package mx.uacj.juego_ra.ui.moleculas

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import mx.uacj.juego_ra.R

@Composable
fun Fondo(){
    Column(modifier = Modifier
        .fillMaxSize()
        //.background(color = Color.Blue)
    ) {
        Image(
            painter = painterResource(R.drawable.fondito),
            contentDescription = "Fondo",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun Margen(){
    Column( modifier=Modifier
        .fillMaxSize()
        //.border(20.dp, shape = RectangleShape, color = Color.DarkGray)

    ) {
        Image(
            painter = painterResource(R.drawable.margen),
            contentDescription = "Margen",
            contentScale = ContentScale.FillHeight,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
fun PantallaRadar(){
    Column( modifier = Modifier
        .fillMaxWidth(0.75f)
        .fillMaxHeight(0.60f)
        .background(color = Color("#1D2025".toColorInt())),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
    }
}

@Composable
fun EsferasConseguidas(){
    Column( modifier = Modifier
        .fillMaxWidth(0.75f)
        .fillMaxHeight(0.20f)
        .background(color = Color("#CAE3EB".toColorInt()))

    ) {  }
}

@Composable
fun EspacioBoton(){
    Column( modifier = Modifier
        .fillMaxWidth(0.75f)
        .fillMaxHeight(0.3f)
        .background(Color("#708D84".toColorInt()))
    )
    {  }
}

@Preview
@Composable
fun PrevisualizarCosas(){
    Box(//modifier = Modifier.fillMaxSize(),
    ){
        Fondo()
        Column(modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            PantallaRadar()
            EsferasConseguidas()
            EspacioBoton()
        }
        Margen()
    }


}