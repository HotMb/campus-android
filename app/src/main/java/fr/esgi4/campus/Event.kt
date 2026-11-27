package fr.esgi4.campus

data class Event(
    val id: Int,
    val title: String,
    val category: String,
    val location: String?,
    val capacity: Int,
    val description: String = ""
)
val events = listOf(
    Event(
        id = 1,
        title = "Conférence Kotlin",
        category = "TECH",
        location = "Salle 204",
        capacity = 3,
        description = "Découvrez Kotlin à travers des démonstrations et des échanges."
    ),
    Event(
        id = 2,
        title = "Tournoi de football",
        category = "SPORT",
        location = null,
        capacity = 1
    ),
    Event(
        id = 3,
        title = "Atelier Jetpack Compose",
        category = "TECH",
        location = "Salle informatique",
        capacity = 0
    ),
    Event(
        id = 4,
        title = "Soirée jeux de société",
        category = "LOISIRS",
        location = "Foyer étudiant",
        capacity = 2
    ),
    Event(
        id = 5,
        title = "Rencontre avec les anciens étudiants",
        category = "CAMPUS",
        location = "Amphithéâtre",
        capacity = 15
    )
)