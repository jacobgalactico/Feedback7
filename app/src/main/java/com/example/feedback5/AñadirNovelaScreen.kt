package com.example.feedback5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun AñadirNovelaScreen(
    navController: NavController,
    viewModel: NovelasViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la novela") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = autor,
            onValueChange = { autor = it },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        TextField(
            value = sinopsis,
            onValueChange = { sinopsis = it },
            label = { Text("Sinopsis") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )
        Button(
            onClick = {
                if (nombre.isNotEmpty() && autor.isNotEmpty() && fecha.isNotEmpty() && sinopsis.isNotEmpty()) {
                    val nuevaNovela = Novela(
                        id = viewModel.novelas.value.size + 1,
                        nombre = nombre,
                        autor = autor,
                        fecha = fecha,
                        sinopsis = sinopsis
                    )
                    viewModel.añadirNovela(nuevaNovela)
                    navController.navigate("listaNovelas")
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir Novela")
        }
    }
}
