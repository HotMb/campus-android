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
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.TextButton
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.material3.Button
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll

private const val TAG = "Campus"

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {
            CampusTheme {
                EventListScreen(events = events)
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
    onBook: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        modifier = modifier,
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFF8FD7CE)
        )
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
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
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
            Text(text = "Places restantes : ${event.capacity}")

            val interactionSource = remember { MutableInteractionSource() }

            val isPressed by interactionSource.collectIsPressedAsState()
            Button(
                onClick = onBook,
                enabled = event.capacity > 0,
                colors = ButtonDefaults.buttonColors(
                    containerColor = if (isPressed) Color(0xFFE1B925)
                    else Color(0xFF2563EB)
                ),
                modifier = Modifier.align(Alignment.CenterHorizontally),
                interactionSource = interactionSource
            ) {
                Text(text = "Réserver")
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
            onBook = {},
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
            onBook = {},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@Preview(
    name = "Événement complet",
    showBackground = true,
    widthDp = 360
)

@Composable
fun EventCardFullPreview() {
    CampusTheme {
        EventCard(
            event = Event(
                id = 3,
                title = "Atelier Jetpack Compose",
                category = "TECH",
                location = "Salle informatique",
                capacity = 0
            ),
            onClick = {},
            onBook = {},
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventListScreen(events: List<Event>) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Campus") }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(
                items = events,
                key = { event -> event.id }
            ) { event ->
                EventCard(
                    event = event,
                    onClick = {
                        Log.d(TAG, "Ouvrir ${event.id}")
                    },
                    onBook = {
                        Log.d(TAG, "Réserver ${event.id}")
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun EventDetailScreen(event: Event, onBook: () -> unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ){
        Text(text = event.title)
        Text(text = event.location ?: "Lieu à confirmer")
        Text(text = event.description)
    }
}

@Preview(showBackground = true, widthDp = 360, heightDp = 640)
@Composable
fun EventDetailScreenPreview() {

}