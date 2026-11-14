package com.example.personaappy.tema1

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// =====================================================================
// 1. COMPONENTE PADRE (Stateful / Controla el Estado)
// Aloja los estados con rememberSaveable y mutableIntStateOf, y los
// transmite hacia abajo junto a sus respectivas lambdas de actualización.
// =====================================================================
@Composable
fun RegisterScreenParent(modifier: Modifier = Modifier) {
    var nombre by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }
    var contadorClicks by rememberSaveable { mutableIntStateOf(0) }

    RegisterScreenContent(
        nombre = nombre,
        onNombreChange = { nombre = it },
        correo = correo,
        onCorreoChange = { correo = it },
        contadorClicks = contadorClicks,
        onIncrementDonation = { contadorClicks++ },
        modifier = modifier
    )
}

// =====================================================================
// 2. COMPONENTE HIJO (Stateless / Visual)
// Recibe los valores ya listos y las funciones lambda para notificar
// los cambios hacia arriba sin almacenar estado interno por su cuenta.
// =====================================================================
@Composable
fun RegisterScreenContent(
    modifier: Modifier = Modifier,
    nombre: String,
    onNombreChange: (String) -> Unit,
    correo: String,
    onCorreoChange: (String) -> Unit,
    contadorClicks: Int,
    onIncrementDonation: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(0xFF3F51B5),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "REGISTRO DE NUEVO USUARIO",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = nombre,
            onValueChange = onNombreChange, // Notifica el cambio de nombre hacia arriba
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Nombre usuario") },
            placeholder = { Text("Introduce tu nombre") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange, // Notifica el cambio de correo hacia arriba
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Correo electrónico") },
            placeholder = { Text("Introduce tu correo electrónico") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(100),
            enabled = nombre.isNotBlank() && correo.isNotBlank(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF3F51B5),
                disabledContainerColor = Color.LightGray
            )
        ) {
            Text(
                text = "Registrarse",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        ElevatedButton(
            onClick = { },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(100),
            elevation = ButtonDefaults.elevatedButtonElevation(
                defaultElevation = 4.dp,
                pressedElevation = 12.dp
            )
        ) {
            Text(text = "Iniciar sesión con Google")
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            OutlinedButton(
                onClick = { },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(100),
                border = BorderStroke(1.5.dp, Color(0xFF3F51B5)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF3F51B5)
                )
            ) {
                Text(text = "Cancelar")
            }

            FilledTonalButton(
                onClick = { },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(100)
            ) {
                Text(text = "Ayuda")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        FilledTonalButton(
            onClick = onIncrementDonation, // Ejecuta la lambda para incrementar el contador en el padre
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(100)
        ) {
            Text(text = "¡Púlsame para donarme!")
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Total de donaciones realizadas: $contadorClicks",
            fontSize = 14.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF3F51B5),
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )

        if (nombre.isNotBlank()) {
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "¡Hola, $nombre! Ya casi estamos.",
                fontSize = 14.sp,
                color = Color.DarkGray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}