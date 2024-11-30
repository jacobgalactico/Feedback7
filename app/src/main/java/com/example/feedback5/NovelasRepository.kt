package com.example.feedback5

import android.content.Context
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val Context.dataStore by preferencesDataStore("novelas_prefs")

class NovelasRepository(private val context: Context) {
    private val NOVELAS_KEY = stringSetPreferencesKey("novelas")

    suspend fun saveNovelas(novelas: List<Novela>) {
        context.dataStore.edit { preferences ->
            preferences[NOVELAS_KEY] = novelas.map { Json.encodeToString(it) }.toSet()
        }
    }

    fun loadNovelas(): Flow<List<Novela>> {
        return context.dataStore.data.map { preferences ->
            val novelasSet = preferences[NOVELAS_KEY] ?: emptySet()
            if (novelasSet.isEmpty()) {
                listOf(
                    Novela(1, "Cien años de soledad", "Gabriel García Márquez", "1967", "Un clásico de la literatura latinoamericana."),
                    Novela(2, "Don Quijote de la Mancha", "Miguel de Cervantes", "1605", "Un viaje épico por la imaginación."),
                    Novela(3, "Orgullo y prejuicio", "Jane Austen", "1813", "Un relato de amor y desafíos sociales.")
                )
            } else {
                novelasSet.map { Json.decodeFromString<Novela>(it) }
            }
        }
    }
}
