package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.grupoa.sparkyoutofbounds.databinding.ActivityNewUserBinding

class NewUserActivity : AppCompatActivity() {
    lateinit var binding: ActivityNewUserBinding
    private lateinit var auth: FirebaseAuth
    var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewUserBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }
    private fun initUi(){
        auth = FirebaseAuth.getInstance()
        currentUser = auth.currentUser

        binding.run {
            btnCreateUser.setOnClickListener {
                val email = editMail.text.toString()
                val pass = editPass.text.toString()
                if(validateData(email, pass))
                    createNewUser(email, pass)
            }
        }
    }

    private fun createNewUser(email: String, password: String){
        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){ task ->
                if(task.isSuccessful){
                    showMessage("Nuevo Usuario Creado")
                    goLoginActivity()
                } else{
                    showMessage("Algo salio mal")
                }
            }
    }

    private fun validateData(email: String, password: String): Boolean {
        var valid = true
        if (email.isEmpty()) {
            valid = false
            showMessage("Ingresa un correo")
        } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            valid = false
            showMessage("Ingresa un correo válido")
        } else if (password.isEmpty()) {
            valid = false
            showMessage("Ingresa una contraseña")
        } else if (password.length < 8) {
            valid = false
            showMessage("Ingresa una contraseña de al menos 8 dígitos")
        }
        return valid
    }

    private fun showMessage(message: String){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun goLoginActivity() {
        val intentRedirect = Intent(this, LoginActivity::class.java)
        startActivity(intentRedirect)
        finish()
    }
}