package com.example.feedback5

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHost
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.feedback5.ui.theme.Feedback5Theme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Feedback5Theme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController: NavHostController = rememberNavController()
    NavHost(navController = navController, startDestination = "listaNovelas") {
        composable("listaNovelas") {
            ListaNovelasScreen(navController)
        }
        composable("detalleNovela/{novelaId}") { backStackEntry ->
            val novelaId = backStackEntry.arguments?.getString("novelaId")?.toIntOrNull() ?: 0
            DetalleNovelaScreen(navController, novelaId)
        }
    }
}
