package com.example.prototype_footprinthero

import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDatabase {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun writeCO2Data(co2Data: List<BarData>, collectionName: String, documentId: String, callback: (Boolean) -> Unit) {
        val co2DataMap = co2Data.associateBy({ it.dayOfWeek }, { it.value })
        db.collection(collectionName)
            .document(documentId)
            .set(co2DataMap)
            .addOnSuccessListener {
                callback(true) // Erfolgreich geschrieben
            }
            .addOnFailureListener { e ->
                callback(false) // Fehler beim Schreiben
            }
    }

    fun readCO2Data(collectionName: String, documentId: String, callback: (List<BarData>?) -> Unit) {
        db.collection(collectionName)
            .document(documentId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val co2DataMap = documentSnapshot.data
                val co2DataList = co2DataMap?.entries?.map { BarData(it.key, it.value as Float) }
                callback(co2DataList)
            }
            .addOnFailureListener { e ->
                callback(null) // Fehler beim Lesen
            }
    }

    fun updateCO2Data(co2Data: List<BarData>, collectionName: String, documentId: String, callback: (Boolean) -> Unit) {
        val co2DataMap = co2Data.associateBy({ it.dayOfWeek }, { it.value })
        db.collection(collectionName)
            .document(documentId)
            .update(co2DataMap)
            .addOnSuccessListener {
                callback(true) // Erfolgreich aktualisiert
            }
            .addOnFailureListener { e ->
                callback(false) // Fehler beim Aktualisieren
            }
    }

    fun deleteCO2Data(collectionName: String, documentId: String, callback: (Boolean) -> Unit) {
        db.collection(collectionName)
            .document(documentId)
            .delete()
            .addOnSuccessListener {
                callback(true) // Erfolgreich gelöscht
            }
            .addOnFailureListener { e ->
                callback(false) // Fehler beim Löschen
            }
    }
}
