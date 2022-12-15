package com.grupoa.sparkyoutofbounds.activities

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.grupoa.sparkyoutofbounds.R

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.grupoa.sparkyoutofbounds.activities.OrderConfirmActivity.Companion.NITFACTURA
import com.grupoa.sparkyoutofbounds.activities.OrderConfirmActivity.Companion.NOMBREFACTURA
import com.grupoa.sparkyoutofbounds.dataClasses.Pizza
import com.grupoa.sparkyoutofbounds.databinding.ActivityOrderMap2Binding

class OrderMapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {
    private lateinit var mMap: GoogleMap
    private lateinit var pizza: Pizza
    private lateinit var binding: ActivityOrderMap2Binding
    private lateinit var nit:String
    private lateinit var name:String

    companion object {
        const val REQUEST_CODE_LOCATION = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityOrderMap2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        pizza = intent.getSerializableExtra(MenuActivity.PIZZA) as Pizza
        nit = intent.getStringExtra(NOMBREFACTURA).orEmpty()
        name = intent.getStringExtra(NITFACTURA).orEmpty()
        setViews()
        setListeners()
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        enableMyLocation()
        mMap.setOnMyLocationButtonClickListener(this)
        mMap.setOnMyLocationClickListener(this)

        val pizzeria = LatLng(-16.523737, -68.108476)
        mMap.moveCamera(CameraUpdateFactory.zoomTo(16.0f))
        //mMap.setMaxZoomPreference(5.0f);
        mMap.addMarker(
            MarkerOptions()
                .position(pizzeria)
                .title("Tu pizza está aca."))

        mMap.moveCamera(CameraUpdateFactory.newLatLng(pizzeria))
    }

    private fun isPermissionsGranted() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun enableMyLocation() {
        if (!::mMap.isInitialized) return
        if (isPermissionsGranted()) {
            mMap.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                mMap.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Para activar la localización ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }


    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::mMap.isInitialized) return
        if(!isPermissionsGranted()){
            mMap.isMyLocationEnabled = false
            Toast.makeText(this, "Para activar la localización ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Boton pulsado", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "A esta dirección de enviaremos tu pedido", Toast.LENGTH_SHORT).show()
        val yourhouse = LatLng(p0.latitude, p0.longitude)
        mMap.addMarker(MarkerOptions()
            .position(yourhouse)
            .title("Tu Pedido Acá."))
    }

    fun setViews(){
        val newText: String
        val precio = pizza.getTotalPrice()
        newText = "${String.format("%.2f",precio)} Bs"
        binding.paidResumeValue.text=newText
        binding.orderResumeContent.text=pizza.title.trim('-')
        if(name.isNotBlank()){
            binding.nitData.text=name
            binding.nameContent.text=nit
        }

    }
    private fun setListeners() {
        binding.confirmButton.setOnClickListener {
            val intent = Intent(this, ThankYouActivity::class.java)
            startActivity(intent)
        }
    }

}