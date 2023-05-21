package com.example.myapplication.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myapplication.viewmodel.MainScreenViewModel

@Preview(showBackground = true)
@Composable
fun MainScreen(viewModel: MainScreenViewModel = viewModel()) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Footprint Hero", style = MaterialTheme.typography.h5)
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding)
        ) {
            FortbewegungsListe(onVehicleSelected = viewModel::onVehicleSelected)
            FortbewegungsDauer(onDurationChanged = viewModel::onDurationChanged)
            CO2Berechnung(
                selectedFortbewegungsmittel = viewModel.selectedFortbewegungsmittel,
                duration = viewModel.duration,
                onCalculateCO2 = viewModel::calculateCO2Emission
            )
            WochentagsUebersicht()
            WochenUebersicht()
        }
    }
}

@Composable
fun FortbewegungsListe(onVehicleSelected: (String) -> Unit) {
    val vehicles = listOf("Auto", "Fahrrad", "Flugzeug")
    val selectedVehicle by remember { mutableStateOf("Auto") }
    Column(Modifier.padding(16.dp)) {
        Text(text = "Fortbewegungsmittel", style = MaterialTheme.typography.h6)
        for (vehicle in vehicles) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                RadioButton(
                    selected = (vehicle == selectedVehicle),
                    onClick = {
                        onVehicleSelected(vehicle)
                    }
                )
                Text(
                    text = vehicle,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun FortbewegungsDauer(onDurationChanged: (Int) -> Unit) {
    val duration by remember { mutableStateOf(0) }
    Column(Modifier.padding(16.dp)) {
        Text(text = "Fortbewegungsdauer (in Minuten)", style = MaterialTheme.typography.h6)
        TextField(
            value = duration.toString(),
            onValueChange = {
                val newValue = it.toIntOrNull() ?: 0
                onDurationChanged(newValue)
            },
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}

@Composable
fun CO2Berechnung(
    selectedFortbewegungsmittel: String,
    duration: Int,
    onCalculateCO2: () -> Unit
) {
    val co2 by remember { mutableStateOf(0f) }
    val fortbewegungsmittelCo2 = mapOf("Auto" to 0.3f, "Fahrrad" to 0.0f, "Flugzeug" to 2.0f)
    val dauer by remember { mutableStateOf(0) }

    Column(Modifier.padding(16.dp)) {
        Text(text = "CO2-Berechnung", style = MaterialTheme.typography.h6)

        Row(Modifier.padding(top = 8.dp)) {
            Text(text = "Gewähltes Fortbewegungsmittel: $selectedFortbewegungsmittel")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Text(text = "Dauer der Fortbewegung: $duration Minuten")
        }

        Button(
            onClick = onCalculateCO2,
            modifier = Modifier.padding(top = 8.dp)
        ) {
            Text(text = "Berechnen")
        }

        Row(Modifier.padding(top = 8.dp)) {
            Text(text = "CO2-Emission: $co2 kg")
        }
    }
}

@Composable
fun WochentagsUebersicht() {
    val days = listOf("Montag", "Dienstag", "Mittwoch", "Donnerstag", "Freitag", "Samstag", "Sonntag")
    val (selectedDay, setSelectedDay) = remember { mutableStateOf(0) }

    Column(Modifier.padding(16.dp)) {
        Text(text = "Wochentagsübersicht", style = MaterialTheme.typography.h6)

        for ((index, day) in days.withIndex()) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                RadioButton(
                    selected = (index == selectedDay),
                    onClick = {
                        setSelectedDay(index)
                    }
                )
                Text(
                    text = day,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}

@Composable
fun WochenUebersicht() {
    val (selectedWeek, setSelectedWeek) = remember { mutableStateOf(0) }

    Column(Modifier.padding(16.dp)) {
        Text(text = "Wochenübersicht", style = MaterialTheme.typography.h6)

        for (week in 1..52) {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp)
            ) {
                RadioButton(
                    selected = (week == selectedWeek),
                    onClick = {
                        setSelectedWeek(week)
                    }
                )
                Text(
                    text = "KW $week",
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(start = 16.dp)
                )
            }
        }
    }
}
