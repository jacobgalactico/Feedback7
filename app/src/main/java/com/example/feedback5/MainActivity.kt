package com.example.feedback5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feedback5.ui.theme.Feedback5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val repository = NovelasRepository(applicationContext) // Repositorio para manejar persistencia

        setContent {
            Feedback5Theme {
                val viewModel: NovelasViewModel = viewModel(
                    factory = NovelasViewModelFactory(repository)
                )
                AppNavigation(viewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: NovelasViewModel) {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "listaNovelas") {
        composable("listaNovelas") {
            ListaNovelasScreen(navController, viewModel)
        }
        composable("añadirNovela") {
            AñadirNovelaScreen(navController, viewModel)
        }
        composable("detalleNovela/{novelaId}") { backStackEntry ->
            val novelaId = backStackEntry.arguments?.getString("novelaId")?.toIntOrNull() ?: 0
            DetalleNovelaScreen(navController, novelaId, viewModel)
        }
    }
}

class NovelasViewModelFactory(
    private val repository: NovelasRepository
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NovelasViewModel::class.java)) {
            return NovelasViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
