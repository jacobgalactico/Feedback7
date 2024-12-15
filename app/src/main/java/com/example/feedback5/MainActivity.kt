package com.example.feedback5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feedback5.ui.theme.Feedback5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = NovelasRepository(applicationContext)

        setContent {
            Feedback5Theme {
                val viewModel = NovelasViewModel(repository)
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "listaNovelas") {
                    composable("listaNovelas") {
                        ListaNovelasScreen(navController, viewModel)
                    }
                    composable("añadirNovela") {
                        AñadirNovelaScreen(navController, viewModel)
                    }
                    composable("detalleNovela/{novelaId}") { backStackEntry ->
                        // Recuperar el ID de la novela desde los argumentos
                        val novelaId = backStackEntry.arguments?.getString("novelaId")?.toIntOrNull() ?: 0
                        DetalleNovelaScreen(navController, novelaId, viewModel)
                    }
                }
            }
        }
    }
}
