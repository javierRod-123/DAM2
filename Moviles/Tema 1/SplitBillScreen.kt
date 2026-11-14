package com.carmen.split_billtipestimator.screens

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

// In the middle (myBillSpliterParent): Receives that modifier as a parameter
// and forwards it down without changing it:
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
        modifier = modifier // Forward the Scaffold's innerPadding modifier here
    )
}

// At the bottom (mySplitBillScreen): Receives the modifier and attaches
// its own layout modifiers (fillMaxSize, padding) onto the end of it:
@Composable
fun mySplitBillScreen(
    modifier: Modifier = Modifier, // Applies Scaffold's innerPadding first (modifier)
    billInput: String,
    onBillInputChange: (String) -> Unit,
    dinersInput: String,
    onDinersInputChange: (String) -> Unit,
    tipPercent: Int,
    onTipPercentChange: (Int) -> Unit
) {
    // MAIN CONTAINER
    // Rule of thumb: Use lowercase modifier once
    // on the outermost container,
    // and capital Modifier on everything inside it.
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp), // , then adds 16.dp
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 1. Banner Header (Box + RoundedCornerShape)
        // Bg: 0xFFE8EAF6 | Text: 0xFF283593 (20.sp Bold)
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp) // Forces a taller fixed height (adjust dp as needed)
                .background(
                    color = Color(0xFFE8EAF6),
                    shape = RoundedCornerShape(12.dp)
                )
                .border(
                    width = 1.5.dp,
                    color = Color(0xFF9FA8DA), // Darker indigo so the border is actually visible!
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

        // 2. Filled TextField (Total Bill)
        // KeyboardType.Decimal + isError if invalid number
        TextField(
            value = billInput,
            // Direct function reference (First TextField):
            // Because onBillInputChange already has the exact signature
            // (String) -> Unit that onValueChange expects, you can pass the
            // function reference directly without curly braces.
            onValueChange = onBillInputChange,
            label = { Text("Total Bill (€)") },
            placeholder = { Text("0.00") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            // Red error border if text is typed but isn't a valid positive double
            isError = billInput.isNotEmpty() && (billInput.toDoubleOrNull() == null || billInput.toDouble() <= 0.0),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(18.dp))

        // 3. OutlinedTextField (Number of Diners)
        // KeyboardType.Number + isError if diners < 1
        OutlinedTextField(
            // Lambda wrapper (Second OutlinedTextField):
            // Wrapping it in { ... } creates an anonymous function (lambda)
            // where it is the new String typed by the user, and inside that block
            // you call onDinersInputChange(it). You use this style when you want
            // to do extra work before passing the value up—such as filtering
            // out non-numeric characters
            // (e.g., { newValue -> if (newValue.all { it.isDigit() }) onDinersInputChange(newValue) }
            value = dinersInput,
            onValueChange = { onDinersInputChange(it) }, // Lambda wrapper forwarding value to callback
            label = { Text("Number of Diners") },
            singleLine = true,
            // Forces the soft keyboard layout to open with integer digits only
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            // Red error border if text is typed but isn't an integer or is less than 1
            isError = dinersInput.isNotEmpty() && (dinersInput.toIntOrNull() == null || dinersInput.toInt() < 1),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(18.dp))

        // Tip Selection Text
        Text(
            text = "Select Tip Percentage:",
            modifier = Modifier.fillMaxWidth(), // NOT fillMaxSize()!!!
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.height(12.dp))

        // 4. Tip Row (Outlined / Filled / Tonal Buttons)
        // Updates tipPercent state (10, 15, or 20)
        // Row container placing 3 buttons side-by-side

        // Original Solution (According to the Exam's Statements)
        // Tip Row with 3 distinct Button variants
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(50.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.spacedBy(12.dp)
//        ) {
//            // 10% -> OutlinedButton (Medium emphasis)
//            OutlinedButton(
//                onClick = { onTipPercentChange(10) },
//                modifier = Modifier.weight(1f).fillMaxHeight(),
//                border = BorderStroke(1.5.dp, Color(0xFF283593)),
//                colors = ButtonDefaults.outlinedButtonColors(
//                    contentColor = Color(0xFF283593)
//                )
//            ) {
//                Text("10%")
//            }
//
//            // 15% -> Filled Button (High emphasis / Primary)
//            Button(
//                onClick = { onTipPercentChange(15) },
//                modifier = Modifier.weight(1f).fillMaxHeight(),
//                colors = ButtonDefaults.buttonColors(
//                    containerColor = Color(0xFF283593),
//                    contentColor = Color.White
//                )
//            ) {
//                Text("15%", fontWeight = FontWeight.Bold)
//            }
//
//            // 20% -> FilledTonalButton (Intermediate emphasis)
//            FilledTonalButton(
//                onClick = { onTipPercentChange(20) },
//                modifier = Modifier.weight(1f).fillMaxHeight(),
//                colors = ButtonDefaults.filledTonalButtonColors(
//                    containerColor = Color(0xFFE8EAF6),
//                    contentColor = Color(0xFF283593)
//                )
//            ) {
//                Text("20%", fontWeight = FontWeight.Bold)
//            }
//        }

        // Dynamic Solution (Cooler, but it isn't what the exam asks for)
        Row(
            modifier = Modifier
                .fillMaxWidth() // Expands row to occupy full available width
                .height(50.dp), // Sets fixed row height so buttons match vertically
            verticalAlignment = Alignment.CenterVertically, // Centers content vertically within the row
            horizontalArrangement = Arrangement.spacedBy(12.dp) // Adds a 12.dp horizontal gap between adjacent buttons
        ) {
            // ==========================================
            // 10% TIP BUTTON
            // ==========================================
            Button(
                onClick = { onTipPercentChange(10) }, // Triggers lambda callback to set parent tip state to 10
                modifier = Modifier
                    .weight(1f) // Allocates exactly 1/3 of total row width
                    .fillMaxHeight(), // Fills full row height
                colors = ButtonDefaults.buttonColors(
                    // Dynamic check: if 10% is active, use solid dark indigo background; otherwise soft tint
                    containerColor = if (tipPercent == 10) Color(0xFF283593) else Color(0xFFE8EAF6),
                    // Dynamic check: white text when active, dark indigo text when unselected
                    contentColor = if (tipPercent == 10) Color.White else Color(0xFF283593)
                )
            ) {
                Text("10%", fontWeight = FontWeight.Bold)
            }

            // ==========================================
            // 15% TIP BUTTON
            // ==========================================
            Button(
                onClick = { onTipPercentChange(15) }, // Triggers lambda callback to set parent tip state to 15
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
            // 20% TIP BUTTON
            // ==========================================
            Button(
                onClick = { onTipPercentChange(20) }, // Triggers lambda callback to set parent tip state to 20
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
        // Clears both inputs and resets tipPercent to 15
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

        // 6. Derived Breakdown Box
        // Shown only when bill > 0.0 and diners >= 1
        val total = billInput.toDoubleOrNull()
        val diners = dinersInput.toIntOrNull()

        // Valid type of values
        if (total != null && diners != null) {
            // Positive values
            if (total > 0 && diners > 0) {
                // Do de Maths
                val totalPlusTip = total + (total * (tipPercent / 100.0))
                val perPerson = totalPlusTip / diners
                // 2. Display inside the breakdown Box
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(120.dp) // Forces a taller fixed height (adjust dp as needed)
                        .background(
                            color = Color(0xFFE0F2F1), // Soft mint/teal fill
                            shape = RoundedCornerShape(12.dp)
                        )
                        .border(
                            width = 1.5.dp,
                            color = Color(0xFF80CBC4), // Medium teal border
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
                            color = Color(0xFF00695C), // Deep teal
                            fontSize = 15.sp,
                        )
                        Text(
                            text = "Each person pays: €${String.format("%.2f", perPerson)}",
                            color = Color(0xFF00695C), // Deep teal
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }
}