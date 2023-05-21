package com.example.myapplication.ui.model

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class `MainScreenModel` : ViewModel() {
    val selectedVehicle = mutableStateOf("Auto")
    val duration = mutableStateOf(0)
    val co2 = mutableStateOf(0f)
    val selectedFortbewegungsmittel = mutableStateOf("Auto")
    val selectedDay = mutableStateOf(0)
    val selectedWeek = mutableStateOf(0)

    fun onVehicleSelected(vehicle: String) {
        selectedVehicle.value = vehicle
    }

    fun onDurationChanged(duration: Int) {
        this.duration.value = duration
    }

    fun calculateCO2Emission() {
        val fortbewegungsmittelCo2 = mapOf("Auto" to 0.3f, "Fahrrad" to 0.0f, "Flugzeug" to 2.0f)
        val co2Emission = fortbewegungsmittelCo2[selectedFortbewegungsmittel.value] ?: 0f
        co2.value = co2Emission * duration.value
    }
}
