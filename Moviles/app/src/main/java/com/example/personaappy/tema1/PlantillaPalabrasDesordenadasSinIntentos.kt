package com.example.personaappy.tema1

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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun JuegoPalabrasEstados(modifier: Modifier = Modifier) {

    // TODO 1: Declara los estados persistentes ante rotación:
    // - nivel (Int)
    // - palabraUsuario (String que el usuario escribirá en el Campo de Texto, empieza vacío)
    // - mensaje (String que se mostrará en el Texto inferior, empieza vacío)
    // - juegoTerminado (Boolean)
    var juegoTerminado = false
    var palabraUsuario by rememberSaveable{ mutableStateOf("") }
    var mensaje by rememberSaveable { mutableStateOf("")}

    // Datos según el nivel actual
    var nivel = 1
    var onNivel by rememberSaveable {mutableIntStateOf(1) }
    val palabraDesordenadaNivel = when (/* TODO: pasa aquí la variable nivel */nivel) {
        1 -> "L O B R A"
        2 -> "R O T A N"
        3 -> "A L E R T S E L"
        else -> ""
    }

    val palabraOrdenadaNivel = when (/* TODO: pasa aquí la variable nivel */nivel) {
        1 -> "ARBOL"
        2 -> "RATON"
        3 -> "ESTRELLA"
        else -> ""
    }

    JuegoPalabrasUI(
        modifier = modifier,
        nivel = nivel,
        onNivel = {nivel = it},
        palabraDesordenadaNivel = palabraDesordenadaNivel,
        palabraUsuario = palabraUsuario,
        mensaje = mensaje,
        juegoTerminado = false,
        onPalabraUsuarioChange = { palabraUsuario = it },
        onClickComprobar = {
            // TODO 2: Implementa la lógica al pulsar "Comprobar"
            if (palabraUsuario == palabraOrdenadaNivel && nivel == 1 ){
                mensaje = "¡Correcto! Siguiente palabra"
                onNivel = 2
            } else if (palabraUsuario == palabraOrdenadaNivel && nivel == 2){
                mensaje = "¡Correcto! Siguiente palabra"
                onNivel = 3
            } else if (palabraUsuario == palabraOrdenadaNivel && nivel == 3){
                mensaje = "¡ENHORABUENA! Has superado el reto"
                juegoTerminado = true
            } else {
                mensaje = "Palabra incorrecta"
            }
        }
    )
}

@Composable
fun JuegoPalabrasUI(
    modifier: Modifier = Modifier,
    nivel: Int,
    onNivel: (Int) -> Unit,
    palabraDesordenadaNivel: String,
    palabraUsuario: String,
    mensaje: String,
    juegoTerminado: Boolean,
    onPalabraUsuarioChange: (String) -> Unit,
    onClickComprobar: () -> Unit
) {
    // TODO 3: Construye la interfaz usando Column, Box, Text, OutlinedTextField, Row, Button y Spacer
    Column(
        modifier = modifier
            .padding(18.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .background(
                    color = Color(0xFFEDE7F6),
                    shape = RoundedCornerShape(22.dp)
                )
                .fillMaxWidth(),
            contentAlignment = Alignment.Center

        ) {
            Text(
                text = "ADIVINA LA PALABRA",
                color =  Color(0xFF4527A0),
                fontSize = (22.sp),
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = "Ordena las letras (Nivel $nivel/3):")

        if (nivel == 1){
            Text(
                text = "L O B R A",
                fontSize = (30.sp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        } else if (nivel == 2){
            Text(
                text = "N O T A R",
                fontSize = (30.sp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        } else if (nivel == 3){
            Text(
                text = "A L L E R T S E",
                fontSize = (30.sp),
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            value = palabraUsuario,
            onValueChange = onPalabraUsuarioChange,
            label = {Text("Tu respuesta")}
        )


        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterHorizontally)
        ) {
            Button(onClick = { onClickComprobar() }) {
                Text(text = "Comprobar")
            }

            Button(
                onClick = {
                    onPalabraUsuarioChange("")
            }) {
                Text(text = "Limpiar")
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(text = mensaje)
    }
}