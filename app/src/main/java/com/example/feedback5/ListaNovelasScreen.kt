package com.example.feedback5

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

@Composable
fun ListaNovelasScreen(navController: NavController) {
    val novelasMock = listOf(
        Novela(1, "Cien años de soledad", "Gabriel García Márquez", "1967", "Un clásico de la literatura latinoamericana."),
        Novela(2, "Don Quijote de la Mancha", "Miguel de Cervantes", "1605", "Un viaje épico por la imaginación."),
        Novela(3, "Orgullo y prejuicio", "Jane Austen", "1813", "Un relato de amor y desafíos sociales.")
    )

    LazyColumn {
        items(novelasMock) { novela ->
            NovelaItem(novela = novela) {
                navController.navigate("detalleNovela/${novela.id}")
            }
        }
    }
}

@Composable
fun NovelaItem(novela: Novela, onClick: () -> Unit) {
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
        }
    }
}
