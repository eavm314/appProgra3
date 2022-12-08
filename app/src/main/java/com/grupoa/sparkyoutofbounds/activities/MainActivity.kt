package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.grupoa.sparkyoutofbounds.R
import com.grupoa.sparkyoutofbounds.databinding.ActivityMainBinding
import com.grupoa.sparkyoutofbounds.databinding.ActivityMakePizzaBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val intent = Intent(this,MakePizzaActivity::class.java)
        startActivity(intent)
    }
}