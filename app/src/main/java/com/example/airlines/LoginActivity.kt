package com.example.airlines

import android.content.Intent
import android.os.Bundle
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class LoginActivity : AppCompatActivity() {

    private var isPasswordVisible: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val etPassword = findViewById<EditText>(R.id.et_password)

        // Configura el ícono de ojo como clickeable
        etPassword.setOnTouchListener { _, event ->
            val drawableEnd = 2
            if (event.rawX >= (etPassword.right - etPassword.compoundDrawables[drawableEnd].bounds.width())) {
                // Detecta el clic en el ícono
                togglePasswordVisibility(etPassword)
                true
            } else {
                false
            }
        }

        val btnLogin = findViewById<Button>(R.id.btn_signin)
        val userName = findViewById<EditText>(R.id.et_user)


        btnLogin.setOnClickListener {
            // Navegar a otra Activity pasando argumentos
            val intent = Intent(this, HomeActivity::class.java)
            intent.putExtra("userName", userName.text.toString())
            startActivity(intent)
        }

    }

    private fun togglePasswordVisibility(passwordEditText: EditText) {
        if (isPasswordVisible) {
            passwordEditText.transformationMethod = PasswordTransformationMethod.getInstance()
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                null, null, ContextCompat.getDrawable(this, R.drawable.ic_eye), null
            )
        } else {
            passwordEditText.transformationMethod = HideReturnsTransformationMethod.getInstance()
            passwordEditText.setCompoundDrawablesWithIntrinsicBounds(
                null, null, ContextCompat.getDrawable(this, R.drawable.ic_closed_eye), null
            )
        }

        passwordEditText.setSelection(passwordEditText.text.length)
        isPasswordVisible = !isPasswordVisible
    }

}