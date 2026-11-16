package com.example.personaappy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.tooling.preview.Preview
import com.example.personaappy.tema1.CalculadoraPropinaParent
import com.example.personaappy.tema1.RegisterScreenParent
import com.example.personaappy.ui.theme.MyApplicationTheme
import tema1.FichaPedidoScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyApplicationTheme {
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    LoginScreen(
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    RegisterScreenParent(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
