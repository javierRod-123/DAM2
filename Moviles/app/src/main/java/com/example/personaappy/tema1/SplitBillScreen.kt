package com.example.personaappy.tema1

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun CalculadoraPropinaParent(modifier: Modifier){
    var cuentaInput by rememberSaveable { mutableStateOf("") }
    var personasInput by rememberSaveable {mutableStateOf("2") }
    var propinaSeleccionada by rememberSaveable { mutableStateOf(15) }

    // LLamo al componente hijo pasandole los valores y lambdas para actualizarlos
    CalculadoraPropinaContent(
        cuenta = cuentaInput,
        onCuentaChanged = {cuentaInput = it},
        personas = personasInput,
        onPersonasChanged = { personasInput = it},
        propina = propinaSeleccionada,
        onPropinaChanged = {propinaSeleccionada = it},
        modifier = modifier
    )
}

// Este componente no guarda estados propios sino que dibuja la pantalla usando los valores
// que le pasa el Padre
@Composable
fun CalculadoraPropinaContent(
    modifier : Modifier = Modifier,
    cuenta: String,
    onCuentaChanged: (String) -> Unit,
    personas: String,
    onPersonasChanged: (String) -> Unit,
    propina: Int,
    onPropinaChanged: (Int) -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .background(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.5.dp,
                    color = Color(0xFF9FA8DA),
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(14.dp),
                contentAlignment = Alignment.Center
        ) {
            Text(
                text = "CALCULADORA DE PROPINAS",
                color = Color(0xFF283593),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        TextField(
            value = cuenta,
            onValueChange = onCuentaChanged,
            label = {Text("Total Cuenta (€)")},
            placeholder = {Text("0.00")},
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            isError = cuenta.isNotEmpty() && (cuenta.toBigDecimalOrNull() == null || cuenta.toDouble() <= 0.0),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        OutlinedTextField(
            value = personas,
            onValueChange = { onPersonasChanged(it) }, // Aquí es donde usas 'personas' y su lambda
            label = { Text("Número de Comensales") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = personas.isNotEmpty() && (personas.toIntOrNull() == null || personas.toInt() < 1),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(18.dp))

        // Etiqueta informativa para elegir la propina
        Text(
            text = "Selecciona el porcentaje de propina:",
            modifier = Modifier.fillMaxWidth(),
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Fila de Botones propina

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Button(
                onClick = {onPropinaChanged(10)},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (propina == 10) Color(0xFF283593) else Color(0xFFE8EAF6),
                    contentColor = if (propina == 10) Color.White else Color(0xFF283593)
                )
            ) {
                Text("10%", fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {onPropinaChanged(15)},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (propina == 15) Color(0xFF283593) else Color(0xFFE8EAF6),
                    contentColor = if (propina == 15) Color.White else Color(0xFF283593)
                )
            ) {
                Text("15%", fontWeight = FontWeight.Bold)
            }

            Button(
                onClick = {onPropinaChanged(20)},
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (propina == 20) Color(0xFF283593) else Color(0xFFE8EAF6),
                    contentColor = if (propina == 20) Color.White else Color(0xFF283593)
                )
            ) {
                Text("20%", fontWeight = FontWeight.Bold)
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Botón de reinicio
        TextButton(
            onClick = {
                onCuentaChanged("")
                onPersonasChanged("2")
                onPropinaChanged(15)
            }
        ) {
            Text(
                text = "Reiniciar Campos",
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                fontSize = 18.sp
            )
        }

        // Caja resultado y Breakdown

        //Convierto el texto introducido a números (Double y Int)
        val totalCuenta = cuenta.toDoubleOrNull()
        val numPersonas = personas.toIntOrNull()

        // Compruebo que ambos campos tengan valores válidos y positivos
        if (totalCuenta != null && numPersonas != null){
            if (totalCuenta > 0.0 && numPersonas > 0){
                val totalConPropina = totalCuenta + (totalCuenta * (propina / 100.0))
                val pagoPorPersona = totalConPropina / numPersonas

                Spacer(modifier = Modifier.height(12.dp))

                // Caja visual para mostrar el desglose estético
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp)
                        .background(
                            color = Color(0xFFE0F2F1),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.5.dp,
                            color = Color(0xFF80CBC4),
                            shape = RoundedCornerShape(12.dp)
                        )
                        .padding(14.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(4.dp)
                    ) {
                        Text(
                            text = "Propina $propina% | Total: €$totalConPropina",
                            color = Color(0xFF00695C),
                            fontSize = 15.sp
                        )

                        Text(
                            text = "Cada persona paga: €${String.format("%.2f", pagoPorPersona)}",
                            color = Color(0xFF00695C),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }

}

