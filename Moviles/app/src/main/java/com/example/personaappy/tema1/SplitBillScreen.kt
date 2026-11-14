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
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// EN LA PARTE SUPERIOR (myBillSpliterParent): Funciona como el componente Padre (Stateful).
// Aloja los estados con rememberSaveable y se los pasa hacia abajo al hijo mediante parámetros y lambdas UDF:
@Composable
fun myBillSpliterParent(modifier: Modifier = Modifier) {
    var billInput by rememberSaveable() { mutableStateOf(value = "") }
    var dinersInput by rememberSaveable() { mutableStateOf(value = "2") }
    var tipPercent by rememberSaveable() { mutableStateOf(value = 15) }

    mySplitBillScreen(
        billInput = billInput,
        onBillInputChange = { billInput = it },
        dinersInput = dinersInput,
        onDinersInputChange = { dinersInput = it },
        tipPercent = tipPercent,
        onTipPercentChange = { tipPercent = it },
        modifier = modifier // Reenvía el modificador de padding del Scaffold aquí
    )
}

// EN LA PARTE INFERIOR (mySplitBillScreen): Funciona como el componente Hijo (Stateless).
// Recibe el modificador y los valores de estado, y emite eventos de subida mediante funciones lambda:
@Composable
fun mySplitBillScreen(
    modifier: Modifier = Modifier, // Aplica el padding inicial del Scaffold (modifier)
    billInput: String,
    onBillInputChange: (String) -> Unit,
    dinersInput: String,
    onDinersInputChange: (String) -> Unit,
    tipPercent: Int,
    onTipPercentChange: (Int) -> Unit
) {
    // CONTENEDOR PRINCIPAL
    // Regla de oro: Usar el modificador en minúscula una sola vez
    // en el contenedor más externo, y la clase Modifier en mayúscula en todo lo demás.
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // , y luego añade 16.dp adicionales de margen interno
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Cabecera con Banner (Box + RoundedCornerShape)
        // Fondo: 0xFFE8EAF6 | Texto: 0xFF283593 (20.sp Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp) // Fuerza una altura fija mayor (ajustable según necesidad)
                .background(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.5.dp,
                    color = Color(0xFF9FA8DA), // Índigo más oscuro para que el borde se distinga bien
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(14.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "SPLIT-BILL CALCULATOR",
                color = Color(0xFF283593),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
        }
        Spacer(modifier = Modifier.height(24.dp))

        // 2. TextField Relleno (Total de la cuenta)
        // KeyboardType.Decimal + isError si el número no es válido
        TextField(
            value = billInput,
            // Referencia directa a función (Primer TextField):
            // Como onBillInputChange ya tiene exactamente la firma esperada
            // (String) -> Unit que exige onValueChange, se puede pasar directamente sin llaves.
            onValueChange = onBillInputChange,
            label = { Text("Total Bill (€)") },
            placeholder = { Text("0.00") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            // Borde rojo de error si se escribe texto pero no es un número double positivo válido
            isError = billInput.isNotEmpty() && (billInput.toDoubleOrNull() == null || billInput.toDouble() <= 0.0),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(18.dp))

        // 3. OutlinedTextField (Número de comensales)
        // KeyboardType.Number + isError si los comensales son menores de 1
        OutlinedTextField(
            // Envoltorio Lambda (Segundo OutlinedTextField):
            // Envolverlo en { ... } crea una función anónima (lambda) donde 'it'
            // es el nuevo texto introducido, permitiendo llamar a onDinersInputChange(it)
            // o realizar operaciones previas (por ejemplo, filtrar solo dígitos numéricos).
            value = dinersInput,
            onValueChange = { onDinersInputChange(it) }, // Envuelve la lambda reenviando el valor al callback
            label = { Text("Number of Diners") },
            singleLine = true,
            // Fuerza la apertura del teclado numérico con solo dígitos enteros
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            // Borde rojo de error si no es un número entero válido o es menor que 1
            isError = dinersInput.isNotEmpty() && (dinersInput.toIntOrNull() == null || dinersInput.toInt() < 1),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(18.dp))

        // Texto de selección de propina
        Text(
            text = "Select Tip Percentage:",
            modifier = Modifier.fillMaxWidth(), // ¡¡NUNCA usar fillMaxSize() aquí!!
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))

        // 4. Fila de Propinas (Botones Outlined / Filled / Tonal)
        // Actualiza el estado tipPercent (10, 15 o 20)
        // Contenedor Row situando 3 botones lado a lado

        // Solución Dinámica (Muy visual e interactiva)
        Row(
            modifier = Modifier
                .fillMaxWidth() // Expande la fila para ocupar todo el ancho disponible
                .height(50.dp), // Fija una altura uniforme para que los botones casen verticalmente
            verticalAlignment = Alignment.CenterVertically, // Centra el contenido verticalmente en la fila
            horizontalArrangement = Arrangement.spacedBy(12.dp) // Añade 12.dp de separación horizontal entre botones
        ) {
            // ==========================================
            // BOTÓN DE PROPINA 10%
            // ==========================================
            Button(
                onClick = { onTipPercentChange(10) }, // Dispara la lambda para poner el estado de propina del padre a 10
                modifier = Modifier
                    .weight(1f) // Reparte el espacio asignando exactamente 1/3 del ancho de la fila
                    .fillMaxHeight(), // Ocupa toda la altura de la fila
                colors = ButtonDefaults.buttonColors(
                    // Comprobación dinámica: si está activo (10%), fondo índigo oscuro; si no, tono suave
                    containerColor = if (tipPercent == 10) Color(0xFF283593) else Color(0xFFE8EAF6),
                    // Texto blanco si está activo, texto índigo oscuro si está deseleccionado
                    contentColor = if (tipPercent == 10) Color.White else Color(0xFF283593)
                )
            ) {
                Text("10%", fontWeight = FontWeight.Bold)
            }

            // ==========================================
            // BOTÓN DE PROPINA 15%
            // ==========================================
            Button(
                onClick = { onTipPercentChange(15) }, // Dispara la lambda para poner el estado de propina del padre a 15
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (tipPercent == 15) Color(0xFF283593) else Color(0xFFE8EAF6),
                    contentColor = if (tipPercent == 15) Color.White else Color(0xFF283593)
                )
            ) {
                Text("15%", fontWeight = FontWeight.Bold)
            }

            // ==========================================
            // BOTÓN DE PROPINA 20%
            // ==========================================
            Button(
                onClick = { onTipPercentChange(20) }, // Dispara la lambda para poner el estado de propina del padre a 20
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (tipPercent == 20) Color(0xFF283593) else Color(0xFFE8EAF6),
                    contentColor = if (tipPercent == 20) Color.White else Color(0xFF283593)
                )
            ) {
                Text("20%", fontWeight = FontWeight.Bold)
            }
        }
        Spacer(modifier = Modifier.height(12.dp))

        // 5. TextButton ("Reset Fields")
        // Limpia ambos campos de texto y restablece la propina a 15%
        TextButton(
            onClick = {
                onBillInputChange("")
                onDinersInputChange("2")
                onTipPercentChange(15)
            }
        ) {
            Text(
                text = "Reset Fields",
                fontWeight = FontWeight.Bold,
                color = Color.Red,
                fontSize = 18.sp
            )
        }

        // 6. Caja de Desglose Derivada (Breakdown)
        // Se muestra únicamente cuando la cuenta > 0.0 y comensales >= 1
        val total = billInput.toDoubleOrNull()
        val diners = dinersInput.toIntOrNull()

        // Validación de tipos correctos
        if (total != null && diners != null) {
            // Valores positivos
            if (total > 0 && diners > 0) {
                // Cálculo matemático de la propina y división
                val totalPlusTip = total + (total * (tipPercent / 100.0))
                val perPerson = totalPlusTip / diners

                // Mostrar dentro del Box de desglose
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp) // Fuerza una altura fija para la caja de resultados
                        .background(
                            color = Color(0xFFE0F2F1), // Relleno verde menta / teal suave
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.5.dp,
                            color = Color(0xFF80CBC4), // Borde color teal medio
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
                            text = "Tip $tipPercent% | Total: €$totalPlusTip",
                            color = Color(0xFF00695C), // Teal profundo
                            fontSize = 15.sp,
                        )
                        Text(
                            text = "Each person pays: €${String.format("%.2f", perPerson)}",
                            color = Color(0xFF00695C), // Teal profundo
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}