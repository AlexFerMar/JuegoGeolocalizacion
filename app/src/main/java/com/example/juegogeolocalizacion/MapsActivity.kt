package com.example.juegogeolocalizacion

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.example.juegogeolocalizacion.databinding.ActivityMapsBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var locationManager: LocationManager? = null
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mapFragment: SupportMapFragment
    private var locationGranted = false

    //Minimo tiempo para updates en Milisegundos
    private val minTimeUpdate = (1000 * 5).toLong() // 5 segundos

    //Minima distancia para updates en metros.
    private val minDistanceUpdate = 1.5 .toFloat() // 1.5 metros

    @SuppressLint("MissingPermission")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        locationGranted = requestLocation()

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)


        locationManager = getSystemService(LOCATION_SERVICE) as LocationManager


        locationManager!!.requestLocationUpdates(
            LocationManager.GPS_PROVIDER,
            minTimeUpdate,
            minDistanceUpdate,
            locListener,
            Looper.getMainLooper()
        )


    }


    var locListener: LocationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {

            val nuevaPosicion = LatLng(location.latitude, location.longitude)
            val cameraPosition = CameraPosition.builder()
                .target(nuevaPosicion)
                .zoom(20f)
                .build()
            mMap.moveCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {

        }

        override fun onProviderEnabled(provider: String) {

        }

        override fun onProviderDisabled(provider: String) {

        }
    }


    @SuppressLint("MissingPermission")
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.uiSettings.isZoomControlsEnabled = true


        if (!locationGranted) return;

        Toast.makeText(this, "El juego va a comenzar!", Toast.LENGTH_SHORT).show()


        mMap.isMyLocationEnabled = true


    }


    fun requestLocation(): Boolean {

        var permission = true

        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            val locationPermissionRequest = registerForActivityResult(
                ActivityResultContracts.RequestMultiplePermissions()
            ) { permissions ->
                when {
                    permissions.getOrDefault(
                        android.Manifest.permission.ACCESS_FINE_LOCATION,
                        false
                    ) -> {

                        Toast.makeText(this, "La localizacion esta activada!", Toast.LENGTH_SHORT)
                            .show()

                    }
                    permissions.getOrDefault(
                        android.Manifest.permission.ACCESS_COARSE_LOCATION,
                        false
                    ) -> {
                        // Only approximate location access granted.
                    }

                    else -> {
                        Toast.makeText(
                            this,
                            "La localizacion esta desactivada. Esta aplicacion necesita tu locaclizacion para funcionar",
                            Toast.LENGTH_SHORT
                        ).show()
                        permission = false
                    }
                }
            }

            locationPermissionRequest.launch(
                arrayOf(
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
                )
            )

        }

        return permission
    }


}