package com.example.feedback5

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.*
import kotlinx.coroutines.launch

@Composable
fun MapaUbicacionScreen() {
    val context = LocalContext.current
    val fusedLocationClient = remember { LocationServices.getFusedLocationProviderClient(context) }
    val scope = rememberCoroutineScope()
    var userLocation by remember { mutableStateOf<LatLng?>(null) }

    LaunchedEffect(Unit) {
        scope.launch {
            userLocation = getCurrentLocation(fusedLocationClient)
        }
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = rememberCameraPositionState {
            position = CameraPosition.fromLatLngZoom(userLocation ?: LatLng(0.0, 0.0), 15f)
        }
    ) {
        userLocation?.let {
            Marker(
                state = MarkerState(position = it),
                title = "Ubicación Actual"
            )
        }
    }
}
