package com.example.feedback5

import android.annotation.SuppressLint
import android.location.Location
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.maps.model.LatLng
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

@SuppressLint("MissingPermission")
suspend fun getCurrentLocation(fusedLocationClient: FusedLocationProviderClient): LatLng? {
    return suspendCancellableCoroutine { continuation ->
        fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
            if (location != null) {
                continuation.resume(LatLng(location.latitude, location.longitude))
            } else {
                val locationRequest = LocationRequest.Builder(1000L)
                    .setMaxUpdates(1)
                    .build()

                val locationCallback = object : LocationCallback() {
                    override fun onLocationResult(result: LocationResult) {
                        result.lastLocation?.let {
                            continuation.resume(LatLng(it.latitude, it.longitude))
                        } ?: continuation.resume(null)
                        fusedLocationClient.removeLocationUpdates(this)
                    }
                }

                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            }
        }.addOnFailureListener {
            continuation.resume(null)
        }
    }
}
