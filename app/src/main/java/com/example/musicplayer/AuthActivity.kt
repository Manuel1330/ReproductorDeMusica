package com.example.musicplayer

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var btnSignUp: Button
    private lateinit var btnLogIn: Button
    private lateinit var editEmail: EditText
    private lateinit var editPassword: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        btnSignUp = findViewById<View>(R.id.signUpButton) as Button
        btnLogIn = findViewById<View>(R.id.logInButton) as Button
        editEmail = findViewById<View>(R.id.emailEditText) as EditText
        editPassword = findViewById<View>(R.id.passwordEditText) as EditText

        //Setup
        setup()
    }

    private fun setup() {
        title = "Autenticación"

        btnSignUp.setOnClickListener {
            if (editEmail.text.isNotEmpty() && editPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                        .createUserWithEmailAndPassword(editEmail.text.toString(),
                        editPassword.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful){
                                showMain(it.result?.user?.email ?:"", CancionType.cancion)
                            }else{
                                showAlert()
                            }
                        }
            }
        }

        btnLogIn.setOnClickListener {
            if (editEmail.text.isNotEmpty() && editPassword.text.isNotEmpty()){
                FirebaseAuth.getInstance()
                        .signInWithEmailAndPassword(editEmail.text.toString(),
                                editPassword.text.toString()).addOnCompleteListener {
                            if (it.isSuccessful){
                                showMain(it.result?.user?.email ?:"", CancionType.cancion)
                            }else{
                                showAlert()
                            }
                        }
            }
        }

    }

    private fun showAlert(){
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Se ha producido un error autenticando al usuario")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun showMain(email: String, cancion: CancionType){
        val mainIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("cancion", cancion.name)
        }
        startActivity(mainIntent)
    }

}