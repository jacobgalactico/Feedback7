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
    viewModel: NovelasViewModel = viewModel()
) {
    // Variables para los campos de texto
    var nombre by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Campo para el nombre de la novela
        TextField(
            value = nombre,
            onValueChange = { nombre = it },
            label = { Text("Nombre de la novela") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Campo para el autor
        TextField(
            value = autor,
            onValueChange = { autor = it },
            label = { Text("Autor") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Campo para la fecha
        TextField(
            value = fecha,
            onValueChange = { fecha = it },
            label = { Text("Fecha") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        // Campo para la sinopsis
        TextField(
            value = sinopsis,
            onValueChange = { sinopsis = it },
            label = { Text("Sinopsis") },
            modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)
        )

        // Botón para agregar la novela
        Button(
            onClick = {
                // Validar que todos los campos estén llenos
                if (nombre.isNotEmpty() && autor.isNotEmpty() && fecha.isNotEmpty() && sinopsis.isNotEmpty()) {
                    val nuevaNovela = Novela(
                        id = (viewModel.novelas.value.size + 1),
                        nombre = nombre,
                        autor = autor,
                        fecha = fecha,
                        sinopsis = sinopsis
                    )
                    viewModel.añadirNovela(nuevaNovela)
                    navController.navigate("listaNovelas") // Regresar a la lista de novelas
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Añadir Novela")
        }
    }
}
