package com.example.firebasfroga.pantailak

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.firebase.firestore.FirebaseFirestore

class IkasleakActivity : ComponentActivity() {

    private val izenak = mutableStateOf("Kargatzen...")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        FirebaseFirestore.getInstance().collection("Alumnos").get()
            .addOnSuccessListener { result ->
                var testua = ""
                for (doc in result) {
                    val izena = doc.getString("Nombre")
                    if (izena != null) {
                        testua += izena + "\n"
                    }
                }
                if (testua.isEmpty()) {
                    izenak.value = "Ez dago daturik 'ikasleak' bilduman"
                } else {
                    izenak.value = testua
                }
            }

        setContent {
            Column(modifier = Modifier.padding(top = 50.dp, start = 20.dp)) {
                Text(text = "Ikasleak:")
                Text(text = izenak.value)

                Button(onClick = { finish() }) {
                    Text("Bueltatu")
                }
            }
        }
    }
}