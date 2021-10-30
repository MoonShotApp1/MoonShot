package com.dartmouth.moonshot

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class LogInActivity: AppCompatActivity() {
    private lateinit var logInButton: Button
    private lateinit var signUpButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private var mFirebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_log_in);

        mFirebaseAuth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.edittext_email)
        passwordEditText = findViewById(R.id.edittext_email)

        signUpButton = findViewById(R.id.button_signup)
        signUpButton.setOnClickListener(){
            signUp()
        }

        logInButton = findViewById(R.id.button_login)
        logInButton.setOnClickListener(){
            logIn()
        }
    }

    fun signUp(){
        val intent = Intent(this, SignUpActivity::class.java)
        startActivity(intent)
    }

    fun logIn(){
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            // TODO
        } else {
            mFirebaseAuth!!.signInWithEmailAndPassword(email, password).addOnCompleteListener(this){
                if(it.isSuccessful){
                    // Log in successful, go back to main activity
                    Log.d("debug", "log in successful")
                    finish()
                } else {
                    // Log in unsuccessful
                    // TODO
                    Log.d("debug", "log in failed")
                }
            }
        }
    }
}