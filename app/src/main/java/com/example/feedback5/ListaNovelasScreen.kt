package com.example.feedback5

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController

@Composable
fun ListaNovelasScreen(
    navController: NavController,
    viewModel: NovelasViewModel = viewModel()
) {
    val novelas = viewModel.novelas.collectAsState().value

    Column {
        Button(
            onClick = { navController.navigate("añadirNovela") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Añadir Novela")
        }

        LazyColumn {
            items(novelas) { novela ->
                NovelaItem(
                    novela = novela,
                    onFavoritoClick = { viewModel.toggleFavorito(novela.id) },
                    onEliminarClick = { viewModel.eliminarNovela(novela.id) },
                    onClick = { navController.navigate("detalleNovela/${novela.id}") }
                )
            }
        }
    }
}

@Composable
fun NovelaItem(
    novela: Novela,
    onFavoritoClick: () -> Unit,
    onEliminarClick: () -> Unit,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = novela.nombre, style = MaterialTheme.typography.titleLarge)
            Text(text = "Autor: ${novela.autor}", style = MaterialTheme.typography.bodyMedium)
            Text(text = "Fecha: ${novela.fecha}", style = MaterialTheme.typography.bodyMedium)
            Row(horizontalArrangement = Arrangement.SpaceBetween, modifier = Modifier.fillMaxWidth()) {
                TextButton(onClick = onFavoritoClick) {
                    Text(if (novela.esFavorita) "❤️ Favorito" else "🤍 Favorito")
                }
                TextButton(onClick = onEliminarClick) {
                    Text("🗑 Eliminar")
                }
            }
        }
    }
}
