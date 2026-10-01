package com.example.firebasfroga.pantailak

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

class MenuActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context = LocalContext.current
            Column(modifier = Modifier.padding(top = 50.dp, start = 20.dp)) {
                Button(onClick = {
                    val intent = Intent(context, IkasleakActivity::class.java)
                    context.startActivity(intent)
                }) {
                    Text("Ikasleak")
                }

                Button(onClick = { finish() }) {
                    Text("Bueltatu")
                }
            }
        }
    }
}