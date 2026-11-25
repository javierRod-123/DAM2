package tema3

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.myapplication.R


@Composable
fun MyImage() {
    Image(
        painter =
            painterResource(
                R.drawable.ic_launcher_foreground
            ),
        contentDescription = "avatar image profile",
                modifier = Modifier.size(100.dp)
    )
}

@Composable
fun MyImageContentScale() {
    Image(
        painter = painterResource(R.drawable.flor),
        contentDescription = "Imagen de flor",
        modifier = Modifier
            .height(200.dp)
            .width(150.dp),
        // Opciones de ContentScale:
        // ContentScale.Crop (Rellena recortando)
        // ContentScale.FillHeight (Ajusta alto)
        // ContentScale.FillWidth (Ajusta ancho)
        contentScale = ContentScale.FillBounds
    )
}

@Composable
fun MyImageShapes() {
    Column {
        // Círculo perfecto
        Image(
            painter = painterResource(R.drawable.flor),
            contentDescription = "Avatar circular",
            modifier = Modifier
                .size(150.dp)
                .clip(CircleShape)
        )

        // Esquinas personalizadas
        Image(
            painter = painterResource(R.drawable.flor),
            contentDescription = "Esquinas asimétricas",
            modifier = Modifier
                .size(200.dp)
                .clip(
                    RoundedCornerShape(
                        topEnd = 30.dp,
                        bottomStart = 50.dp
                    )
                )
        )
    }
}

@Composable
fun MyImageWithGradientBorder() {
    Image(
        painter = painterResource(R.drawable.flor),
        contentDescription = "Imagen con borde degradado",
        modifier = Modifier
            .size(250.dp)
            .clip(RoundedCornerShape(topEnd = 30.dp, bottomStart =
                50.dp))
            .border(
                width = 5.dp,
                brush = Brush.linearGradient(
                    colors = listOf(Color.Green, Color.Blue,
                        Color.Cyan)
                ),
                shape = RoundedCornerShape(topEnd = 30.dp, bottomStart
                = 50.dp)
            )
    )
}

