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
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp

private const val TAG = "Campus"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CampusTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        val event = Event(
                            id = 1,
                            title = "Tournoi de football",
                            category = "SPORT",
                            location = null,
                            capacity = 12
                        )

                        EventCard(
                            event = event,
                            onClick = {
                                Log.d(TAG, "Ouvrir ${event.id}")
                            },
                            modifier = Modifier
                                .padding(16.dp)
                                .fillMaxWidth()
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

@Composable
fun EventCard(
    event: Event,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = event.title,
                    modifier = Modifier.weight(1f)
                )

                Text(text = event.category)
            }

            Text(
                text = event.location ?: "Lieu à confirmer"
            )

            TextButton(
                onClick = onClick,
                modifier = Modifier.align(Alignment.End)
            ) {
                Text(text = "Voir")
            }
        }
    }
}

@Preview(
    name = "Sans lieu",
    showBackground = true,
    widthDp = 360
)
@Composable
fun EventCardWithoutLocationPreview() {
    MaterialTheme {
        EventCard(
            event = Event(
                id = 2,
                title = "Tournoi de football",
                category = "SPORT",
                location = null,
                capacity = 12
            ),
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(
    name = "Titre long",
    showBackground = true,
    widthDp = 360
)
@Composable
fun EventCardLongTitlePreview() {
    CampusTheme {
        EventCard(
            event = Event(
                id = 3,
                title = "Conférence Kotlin et Android : apprendre à créer des applications pour le campus !",
                category = "TECH",
                location = "Salle 204",
                capacity = 30
            ),
            onClick = {},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}