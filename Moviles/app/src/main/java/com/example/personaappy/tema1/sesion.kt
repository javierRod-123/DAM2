package com.example.personaappy.tema1

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// =====================================================================
// COMPONENTE PADRE (Stateful / Controla el Estado)
// Aloja las variables con rememberSaveable y las pasa hacia abajo junto a sus lambdas
// =====================================================================
@Composable
fun LoginScreenParent(modifier: Modifier = Modifier) {
    var correo by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }
    var ocultarPass by rememberSaveable { mutableStateOf(true) }

    LoginScreenContent(
        correo = correo,
        onCorreoChange = { correo = it },
        password = password,
        onPasswordChange = { password = it },
        ocultarPass = ocultarPass,
        onTogglePasswordVisibility = { ocultarPass = !ocultarPass },
        modifier = modifier
    )
}

// =====================================================================
// COMPONENTE HIJO (Stateless / Visual)
// Recibe los valores y las funciones lambda para notificar los cambios
// =====================================================================
@Composable
fun LoginScreenContent(
    modifier: Modifier = Modifier,
    correo: String,
    onCorreoChange: (String) -> Unit,
    password: String,
    onPasswordChange: (String) -> Unit,
    ocultarPass: Boolean,
    onTogglePasswordVisibility: () -> Unit
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
                    color = Color(0xFFE8F5E9),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ){
            Text(
                text = "ACCESO DE USUARIO",
                color = Color(0xFF2E7D32),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = correo,
            onValueChange = onCorreoChange, // Envía el cambio hacia arriba mediante la lambda del padre
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Correo electrónico") },
            placeholder = { Text("Introduce tu correo") }
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = password,
            onValueChange = onPasswordChange, // Envía el cambio de contraseña hacia arriba
            modifier = Modifier.fillMaxWidth(),
            singleLine = true,
            label = { Text("Contraseña") },
            placeholder = { Text("Introduce tu contraseña") },
            visualTransformation = if (ocultarPass) PasswordVisualTransformation() else VisualTransformation.None,
            trailingIcon = {
                Text(
                    text = if (ocultarPass) "Mostrar" else "Ocultar",
                    color = Color(0xFF2E7D32),
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .padding(end = 8.dp)
                        .clickable { onTogglePasswordVisibility() } // Llama a la lambda para alternar visibilidad
                )
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                // Acción al iniciar sesión
            },
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(100.dp),
            enabled = correo.isNotBlank() && password.length >= 4
        ) {
            Text(
                text = "Iniciar sesión",
                fontSize = 16.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = {
                // Acción de contraseña olvidada
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(
                text = "¿Has olvidado tu contraseña?",
                color = Color(0xFF2E7D32)
            )
        }

        if (correo.isNotBlank()){
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Conectándose como: $correo",
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )
        }
    }
}