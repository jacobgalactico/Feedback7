package com.example.feedback5

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun DetalleNovelaScreen(
    navController: NavController,
    novelaId: Int,
    viewModel: NovelasViewModel = viewModel()
) {
    val novelas = viewModel.novelas.collectAsState().value
    val novela = novelas.firstOrNull { it.id == novelaId }

    Column(modifier = Modifier.padding(16.dp)) {
        novela?.let {
            Text(text = it.nombre, style = MaterialTheme.typography.titleLarge)
            Text(text = "Autor: ${it.autor}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: ${it.fecha}", style = MaterialTheme.typography.bodyMedium)
            Text(
                text = it.sinopsis,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 16.dp)
            )
            Button(
                onClick = { viewModel.toggleFavorito(it.id) },
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Text(if (it.esFavorita) "❤️ Quitar Favorito" else "🤍 Marcar Favorito")
            }
        } ?: run {
            Text(text = "Novela no encontrada.", style = MaterialTheme.typography.titleLarge)
        }
    }
}
