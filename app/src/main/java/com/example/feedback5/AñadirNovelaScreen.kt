package com.example.feedback5

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.launch

@Composable
fun AñadirNovelaScreen(
    navController: NavController,
    viewModel: NovelasViewModel
) {
    var nombre by remember { mutableStateOf("") }
    var autor by remember { mutableStateOf("") }
    var fecha by remember { mutableStateOf("") }
    var sinopsis by remember { mutableStateOf("") }
    var ubicacion by remember { mutableStateOf<LatLng?>(null) }

    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val scope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        TextField(value = nombre, onValueChange = { nombre = it }, label = { Text("Nombre") })
        TextField(value = autor, onValueChange = { autor = it }, label = { Text("Autor") })
        TextField(value = fecha, onValueChange = { fecha = it }, label = { Text("Fecha") })
        TextField(value = sinopsis, onValueChange = { sinopsis = it }, label = { Text("Sinopsis") })

        Button(onClick = {
            scope.launch {
                ubicacion = getCurrentLocation(fusedLocationClient)
            }
        }) {
            Text("Obtener Ubicación")
        }

        Text("Ubicación: ${ubicacion?.latitude}, ${ubicacion?.longitude}")

        Button(onClick = {
            val nuevaNovela = Novela(
                id = viewModel.novelas.value.size + 1,
                nombre = nombre,
                autor = autor,
                fecha = fecha,
                sinopsis = sinopsis,
                latitud = ubicacion?.latitude,
                longitud = ubicacion?.longitude
            )
            viewModel.añadirNovela(nuevaNovela)
            navController.navigate("listaNovelas")
        }) {
            Text("Añadir Novela")
        }
    }
}
