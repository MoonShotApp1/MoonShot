package com.dartmouth.moonshot

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity: AppCompatActivity() {
    private lateinit var logInButton: Button
    private lateinit var signUpButton: Button
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private var mFirebaseAuth: FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)


        mFirebaseAuth = FirebaseAuth.getInstance()

        emailEditText = findViewById(R.id.edittext_email)
        passwordEditText = findViewById(R.id.edittext_email)

        signUpButton = findViewById(R.id.button_signup)
        signUpButton.setOnClickListener(){
            signUp()
        }
    }

    fun signUp(){
        val email = emailEditText.text.toString()
        val password = passwordEditText.text.toString()

        if (email.isEmpty() || password.isEmpty()) {
            val myDialog = LogInDialog()
            val bundle = Bundle()
            bundle.putInt(LogInDialog.DIALOG_KEY, LogInDialog.NO_EMAIL_PASSWORD_INT)
            myDialog.arguments = bundle
            myDialog.show(supportFragmentManager, "Sign Up Failed")
        } else {
            mFirebaseAuth!!.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this){
                if(it.isSuccessful){
                    // Sign up successful
                    Log.d("debug", "sign up successful")
                    finish()
                } else {
                    // Sign up failed
                    Log.d("debug", "sign up failed")
                    Log.d("debug", it.exception.toString())
                    val myDialog = LogInDialog()
                    val bundle = Bundle()
                    bundle.putInt(LogInDialog.DIALOG_KEY, LogInDialog.SIGNUP_INT)
                    myDialog.arguments = bundle
                    myDialog.show(supportFragmentManager, "Sign Up Failed")
                }
            }
        }
    }
}