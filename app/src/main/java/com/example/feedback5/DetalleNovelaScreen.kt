package com.example.feedback5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun DetalleNovelaScreen(
    navController: NavController,
    novelaId: Int,
    viewModel: NovelasViewModel
) {
    val novelas = viewModel.novelas.collectAsState().value
    val novela = novelas.firstOrNull { it.id == novelaId }

    Column(modifier = Modifier.padding(16.dp)) {
        if (novela != null) {
            Text(text = novela.nombre, style = MaterialTheme.typography.titleLarge)
            Text(text = "Autor: ${novela.autor}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: ${novela.fecha}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = novela.sinopsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(
                onClick = { navController.navigateUp() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        } else {
            Text(
                text = "No se encontró la novela con ID: $novelaId",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error
            )
            Button(
                onClick = { navController.navigateUp() },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text("Volver")
            }
        }
    }
}
