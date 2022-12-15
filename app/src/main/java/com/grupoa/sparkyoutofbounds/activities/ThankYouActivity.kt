package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.grupoa.sparkyoutofbounds.databinding.ActivityThankYouBinding

class ThankYouActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThankYouBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThankYouBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListeners()

    }

    private fun setListeners(){
        binding.sendMenu.setOnClickListener {
            startActivity(
                Intent(this, MenuActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
            )
            finish()
        }
    }
}