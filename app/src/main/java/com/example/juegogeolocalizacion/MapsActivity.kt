package com.example.juegogeolocalizacion

import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.graphics.Color
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
import com.google.android.gms.maps.model.CircleOptions
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var locationManager: LocationManager? = null
    private lateinit var binding: ActivityMapsBinding
    private lateinit var mapFragment: SupportMapFragment
    private var locationGranted = false


    //region tesosros

    var tesoro1 = Location("tesoro1")
    var tesoro2 = Location("tesoro2")
    var tesoro3 = Location("tesoro3")

    init {
        tesoro1.latitude = 42.23699434605001
        tesoro1.longitude = -8.714858173423254

        tesoro2.latitude = 42.23478669846102
        tesoro2.longitude = -8.719721794117492

        tesoro3.latitude = 42.241187652778876
        tesoro3.longitude = -8.72290017054465

    }

    private val locations = listOf(tesoro1, tesoro2, tesoro3)

    //endregion


    //Minimo tiempo para updates en Milisegundos
    private val minTimeUpdate = (1000 * 5).toLong() // 5 segundos

    //Minima distancia para updates en metros.
    private val minDistanceUpdate = 1.5.toFloat() // 1.5 metros

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

            searchTreasure(location)

        }

        private fun searchTreasure(location: Location) {

            var distancia:Float

            for (location2 in locations) {

                distancia = location.distanceTo(location2)

                if (distancia < 3) {
                    mMap.clear();
                    locations.drop(locations.indexOf(location2))

                } else if (distancia < 10) {
                    mMap.clear();

                    mMap.addMarker(
                        MarkerOptions()
                            .position(LatLng(location2.latitude, location2.longitude))
                            .title("Tesoro")
                    )


                } else if (distancia < 100) {
                    mMap.clear();
                    drawCircle(
                        LatLng(
                            location2.latitude ,
                            location2.longitude
                        ), 100.0
                    )

                }

            }


        }


        private fun drawCircle(point: LatLng, radius: Double) {
            val circleOptions = CircleOptions()
            circleOptions.center(point)
            circleOptions.radius(radius)
            circleOptions.strokeColor(Color.RED)
            circleOptions.strokeWidth(3F)
            mMap.addCircle(circleOptions)
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
                        Toast.makeText(this, "La localizacion esta activada!", Toast.LENGTH_SHORT)
                            .show()
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