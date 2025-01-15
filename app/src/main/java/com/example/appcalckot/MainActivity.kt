package com.example.appcalckot

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.appcalckot.ui.theme.AppCalcKotTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppCalcKotTheme {
                CalculadoraApp()
            }
        }
    }
}

@Composable
fun CalculadoraApp() {
    // Variables de estado para entrada y resultado
    var numero1 by remember { mutableStateOf("") }
    var numero2 by remember { mutableStateOf("") }
    var resultado by remember { mutableStateOf<String?>(null) }
    var mensajeError by remember { mutableStateOf<String?>(null) }

    Scaffold(modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Campo de texto para el primer número
            TextField(
                value = numero1,
                onValueChange = { numero1 = it },
                label = { Text("Número 1") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Campo de texto para el segundo número
            TextField(
                value = numero2,
                onValueChange = { numero2 = it },
                label = { Text("Número 2") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Botón para realizar la suma
            Button(onClick = {
                mensajeError = null
                resultado = null
                try {
                    val num1 = numero1.toDouble()
                    val num2 = numero2.toDouble()
                    resultado = "Resultado: ${num1 + num2}"
                } catch (e: NumberFormatException) {
                    mensajeError = "Por favor, ingresa números válidos."
                }
            }) {
                Text(text = "Sumar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Mostrar mensajes de error si los hay
            mensajeError?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.error,
                    fontSize = 16.sp
                )
            }

            // Mostrar el resultado si existe
            resultado?.let {
                Text(
                    text = it,
                    fontSize = 20.sp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculadoraAppPreview() {
    AppCalcKotTheme {
        CalculadoraApp()
    }
}
