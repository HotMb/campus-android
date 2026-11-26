package fr.esgi4.campus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.esgi4.campus.ui.theme.CampusTheme
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme

private const val TAG = "Campus"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            CampusTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        EventHeader(
                            title = "Tournoi de football",
                            category = "Sport",
                            location = null
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CampusTheme {
        Greeting("Otmane")
    }
}

@Composable
fun EventHeader(
    title: String,
    category: String,
    location: String?
) {
    Column {
        Text(text = title)
        Text(text = category)
        Text(text = location ?: "Lieu à confirmer")
    }
}

@Preview(showBackground = true)
@Composable
fun EventHeaderPreview() {
    MaterialTheme {
        EventHeader(
            title = "Tournoi de football",
            category = "Sport",
            location = null
        )
    }
}