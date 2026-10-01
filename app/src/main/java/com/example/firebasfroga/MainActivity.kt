package com.example.firebasfroga

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.firebasfroga.pantailak.MenuActivity
import com.example.firebasfroga.pantailak.RegisterActivity
import com.example.firebasfroga.ui.theme.FirebasFrogaTheme
import com.google.firebase.auth.FirebaseAuth

class MainActivity : ComponentActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize Firebase Auth
        auth = FirebaseAuth.getInstance()
        enableEdgeToEdge()
        setContent {
            FirebasFrogaTheme {
                AppNavigation(auth)
            }
        }
    }
}

@Composable
fun AppNavigation(auth: FirebaseAuth) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 50.dp),
    ) {
        Spacer(modifier = Modifier.padding(25.dp))

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            singleLine = true,
            label = { Text("Email") },
        )
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            singleLine = true,
            label = { Text("Password") },
        )
        Spacer(modifier = Modifier.padding(5.dp))
        Button(onClick = {
            if (email.isNotEmpty() && password.isNotEmpty()) {
                auth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            val intent = Intent(context, MenuActivity::class.java)
                            context.startActivity(intent)
                        } else {
                            Toast.makeText(context, "Login errorea: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(context, "Bete emaila eta pasahitza!", Toast.LENGTH_SHORT).show()
            }
        }) {
            Text(text = "Login")
        }
        Button(onClick = {
            val intent = Intent(context, RegisterActivity::class.java)
            context.startActivity(intent)
        }) {
            Text("Registratu")
        }
    }
}