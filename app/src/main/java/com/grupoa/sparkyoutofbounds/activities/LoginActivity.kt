package com.grupoa.sparkyoutofbounds.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.grupoa.sparkyoutofbounds.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    var currentUser: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUi()
        binding.btnGoToNewUser.setOnClickListener {
            val intent = Intent(this, NewUserActivity::class.java)
            startActivity(intent)
        }
    }
    private fun initUi(){
        auth = FirebaseAuth.getInstance()
        currentUser = auth.currentUser

        binding.run {
            btnSendLogin.setOnClickListener {
                val email = emailUser.text.toString()
                val pass = passUser.text.toString()

                if (currentUser == null) {
                    showMessage("Debes tener una sesión iniciada para continuar")
                }

                if(validateData(email, pass))
                    loginUser(email, pass)



            }

        }
    }



    private fun loginUser(email: String, password: String){
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this){task ->
                if(task.isSuccessful){
                    redirectActivity()
                } else{
                    showMessage("Usuario o contraseña incorrectos")
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

    private fun redirectActivity() {
        val intentRedirect = Intent(this, MenuActivity::class.java)
        startActivity(intentRedirect)
        finish()
    }

}