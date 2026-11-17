package com.aplimoviles.probandolayouts.examenu1u2202627

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun JuegoPalabrasEstados(modifier: Modifier = Modifier) {

    // TODO 1: Declara los estados persistentes ante rotación:
    // - nivel (Int)
    // - palabraUsuario (String que el usuario escribirá en el Campo de Texto, empieza vacío)
    // - mensaje (String que se mostrará en el Texto inferior, empieza vacío)
    // - juegoTerminado (Boolean)


    // Datos según el nivel actual
    val varABorrar=(1..3).random()
    val palabraDesordenadaNivel = when (/* TODO: pasa aquí la variable nivel */varABorrar) {
        1 -> "L O B R A"
        2 -> "R O T A N"
        3 -> "A L E R T S E L"
        else -> ""
    }

    val palabraOrdenadaNivel = when (/* TODO: pasa aquí la variable nivel */varABorrar) {
        1 -> "ARBOL"
        2 -> "RATON"
        3 -> "ESTRELLA"
        else -> ""
    }

    JuegoPalabrasUI(
        modifier = modifier,
        nivel = /* TODO */0,
        palabraDesordenadaNivel = palabraDesordenadaNivel,
        palabraUsuario = /* TODO */"",
        mensaje = /* TODO */"",
        juegoTerminado = /* TODO */ false,
        onPalabraUsuarioChange = { /* TODO */ },
        onClickComprobar = {
            // TODO 2: Implementa la lógica al pulsar "Comprobar"
        }
    )
}

@Composable
fun JuegoPalabrasUI(
    modifier: Modifier = Modifier,
    nivel: Int,
    palabraDesordenadaNivel: String,
    palabraUsuario: String,
    mensaje: String,
    juegoTerminado: Boolean,
    onPalabraUsuarioChange: (String) -> Unit,
    onClickComprobar: () -> Unit
) {
    // TODO 3: Construye la interfaz usando Column, Box, Text, OutlinedTextField, Row, Button y Spacer

}