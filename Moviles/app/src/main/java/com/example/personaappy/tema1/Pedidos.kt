package tema1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun FichaPedidoScreen(modifier: Modifier) {

    var nombreProducto by remember { mutableStateOf("") }
    var cantidad by remember { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color(0xFFE3F2FD),
                shape = RoundedCornerShape(12.dp)
            )
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = "FICHA DE PEDIDO",
            color = Color(0xFF1565C0),
            fontSize = 22.sp,
            fontWeight = FontWeight.SemiBold
        )
    }
       Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = nombreProducto,
            onValueChange = { nuevoTexto -> nombreProducto = nuevoTexto},
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre del producto")}
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Cantidad",
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Button(onClick = {
                if (cantidad > 0) cantidad--
            }){
                Text(text = "-", fontSize = 18.sp)
            }

            Spacer(modifier = Modifier.width(16.dp))

            Text(
                text = cantidad.toString(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.width(16.dp))

            Button(onClick = {
                cantidad++
            }) {
                Text(
                    text = "+",
                    fontSize = 18.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (nombreProducto.isNotBlank() && cantidad > 0){
            Text(
                text = "Resumen: $cantidad unidad(es) de '$nombreProducto'",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                color = Color(0xFF1565C0)
            )
        }
    }
}