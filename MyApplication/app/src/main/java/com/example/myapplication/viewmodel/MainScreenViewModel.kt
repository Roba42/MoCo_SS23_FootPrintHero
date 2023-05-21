package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel

class MainScreenViewModel : ViewModel() {
    var selectedFortbewegungsmittel: String = "Auto"
    var duration: Int = 0

    fun onVehicleSelected(vehicle: String) {
        selectedFortbewegungsmittel = vehicle
    }

    fun onDurationChanged(newDuration: Int) {
        duration = newDuration
    }

    fun calculateCO2Emission() {
        // Implementieren Sie hier Ihre CO2-Berechnungslogik
    }
}
