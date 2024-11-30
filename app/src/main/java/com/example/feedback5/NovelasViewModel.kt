package com.example.feedback5

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NovelasViewModel(private val repository: NovelasRepository) : ViewModel() {
    private val _novelas = MutableStateFlow<List<Novela>>(emptyList())
    val novelas: StateFlow<List<Novela>> = _novelas

    init {
        // Cargar novelas al iniciar
        viewModelScope.launch {
            repository.loadNovelas().collect { loadedNovelas ->
                _novelas.value = loadedNovelas
            }
        }
    }

    fun añadirNovela(novela: Novela) {
        viewModelScope.launch {
            try {
                val nuevasNovelas = _novelas.value.toMutableList().apply { add(novela) }
                _novelas.value = nuevasNovelas
                repository.saveNovelas(nuevasNovelas)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun eliminarNovela(novelaId: Int) {
        viewModelScope.launch {
            try {
                val nuevasNovelas = _novelas.value.toMutableList().apply {
                    removeAll { it.id == novelaId }
                }
                _novelas.value = nuevasNovelas
                repository.saveNovelas(nuevasNovelas)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun toggleFavorito(novelaId: Int) {
        viewModelScope.launch {
            try {
                val nuevasNovelas = _novelas.value.map { novela ->
                    if (novela.id == novelaId) novela.copy(esFavorita = !novela.esFavorita) else novela
                }
                _novelas.value = nuevasNovelas
                repository.saveNovelas(nuevasNovelas)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
