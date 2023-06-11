package com.example.test


import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDatabase {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun writeCO2Data(
        co2Data: List<BarData>,
        collectionName: String,
        documentId: String,
        callback: (Boolean, String?) -> Unit
    ) {
        val data = co2Data.map { it.copy() } // Kopie der Liste erstellen, um unerwartete Änderungen zu vermeiden

        val firestoreData = data.map { barData ->
            mapOf(
                "dayOfWeek" to barData.dayOfWeek,
                "value" to barData.value
            )
        }

        db.collection(collectionName)
            .document(documentId)
            .set(mapOf("co2Data" to firestoreData))
            .addOnSuccessListener {
                callback(true, null) // Erfolgreich geschrieben, kein Fehler
            }
            .addOnFailureListener { e ->
                val errorMessage = e.message ?: "Unbekannter Fehler"
                callback(false, errorMessage) // Fehler beim Schreiben mit Fehlermeldung
            }
    }




    fun readCO2Data(
        collectionName: String,
        documentId: String,
        callback: (List<BarData>?) -> Unit
    ) {
        db.collection(collectionName)
            .document(documentId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val firestoreData = documentSnapshot.get("co2Data") as? List<Map<String, Any>>
                val co2DataList = firestoreData?.map { data ->
                    BarData(
                        dayOfWeek = data["dayOfWeek"] as? String ?: "",
                        value = (data["value"] as? Double)?.toFloat() ?: 0f
                    )
                }
                callback(co2DataList)
            }
            .addOnFailureListener { e ->
                callback(null) // Fehler beim Lesen
            }
    }

    fun updateCO2Data(
        co2Data: List<BarData>,
        collectionName: String,
        documentId: String,
        callback: (Boolean) -> Unit
    ) {
        val data = co2Data.map { it.copy() } // Kopie der Liste erstellen, um unerwartete Änderungen zu vermeiden

        val firestoreData = data.map { barData ->
            mapOf(
                "dayOfWeek" to barData.dayOfWeek,
                "value" to barData.value
            )
        }

        db.collection(collectionName)
            .document(documentId)
            .update(mapOf("co2Data" to firestoreData))
            .addOnSuccessListener {
                callback(true) // Erfolgreich aktualisiert
            }
            .addOnFailureListener { e ->
                callback(false) // Fehler beim Aktualisieren
            }
    }

    fun deleteCO2Data(
        collectionName: String,
        documentId: String,
        callback: (Boolean) -> Unit
    ) {
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
