package com.example.feedback5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NovelasViewModel : ViewModel() {
    // Lista de novelas observable
    private val _novelas = MutableStateFlow(
        mutableListOf(
            Novela(1, "Cien años de soledad", "Gabriel García Márquez", "1967", "Un clásico de la literatura latinoamericana."),
            Novela(2, "Don Quijote de la Mancha", "Miguel de Cervantes", "1605", "Un viaje épico por la imaginación."),
            Novela(3, "Orgullo y prejuicio", "Jane Austen", "1813", "Un relato de amor y desafíos sociales.")
        )
    )
    val novelas: StateFlow<List<Novela>> = _novelas

    // Añadir una nueva novela
    fun añadirNovela(novela: Novela) {
        viewModelScope.launch {
            _novelas.value = _novelas.value.toMutableList().apply {
                add(novela)
            }
        }
    }

    // Eliminar una novela por su ID
    fun eliminarNovela(novelaId: Int) {
        viewModelScope.launch {
            _novelas.value = _novelas.value.toMutableList().apply {
                removeAll { it.id == novelaId }
            }
        }
    }

    // Marcar o desmarcar una novela como favorita
    fun toggleFavorito(novelaId: Int) {
        viewModelScope.launch {
            _novelas.value = _novelas.value.map { novela ->
                if (novela.id == novelaId) novela.copy(esFavorita = !novela.esFavorita) else novela
            }.toMutableList()
        }
    }

}
