package com.example.personaappy.tema1


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun TaquillaCinePadre(modifier: Modifier){
    var peliculaInput by rememberSaveable { mutableStateOf("") }
    var entradasSeleccionadas by rememberSaveable { mutableIntStateOf(0) }

    TaquillaCineHijo(
        pelicula = peliculaInput,
        onPeliculaChanged = {peliculaInput = it},
        entradas = entradasSeleccionadas,
        onEntradasChanged = {entradasSeleccionadas = it},
        modifier = modifier,
    )
}

@Composable
fun TaquillaCineHijo(
    modifier: Modifier,
    pelicula : String,
    onPeliculaChanged : (String) -> Unit,
    entradas : Int,
    onEntradasChanged : (Int) -> Unit,
){
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF1A237E),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "TAQUILLA DE CINE",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = pelicula,
            onValueChange = onPeliculaChanged,
            label = {Text("Nombre de la película:")},
            singleLine = true
        )

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally) // Ajustar la distacia de los button
        ) {
            Button(
                onClick = {
                    if (entradas > 0){
                        onEntradasChanged(entradas - 1)
                    }
                },
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF283593),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "-",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }

            if (entradas > 0) {
                Text(
                    text = "$entradas",
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    color = Color(0xFF1A237E)
                )
            }
            Button(
                onClick = {
                    onEntradasChanged(entradas + 1)
                },
                modifier = Modifier.size(52.dp),
                shape = RoundedCornerShape(14.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF283593),
                    contentColor = Color.White
                )
            ) {
                Text(
                    text = "+",
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        if (entradas > 0 && pelicula.isNotBlank()){
            val precioTotal = entradas * 7.20

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
                    .background(
                        color = Color(0xFFE8EAF6),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.5.dp,
                        color = Color(0xFF9FA8DA),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ){
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(6.dp)
                ) {
                    Text(
                    text = "Película: $pelicula ($entradas ent.)",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp,
                    color = Color(0xFF1A237E),
                    textAlign = TextAlign.Center
                )
                    Text(
                        text = "Total a pagar: ${String.format("%.2f", precioTotal)} €",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp,
                        color = Color(0xFF00695C),
                        textAlign = TextAlign.Center
                    )
                }


            }
        }

    }
}