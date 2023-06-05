package com.example.prototype_footprinthero

import com.google.firebase.firestore.FirebaseFirestore

class FirestoreDatabase {

    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()

    fun writeData(data: Any) {
        db.collection("yourCollection")
            .document("yourDocumentId")
            .set(data)
            .addOnSuccessListener {
                // Erfolgreich geschrieben
            }
            .addOnFailureListener { e ->
                // Fehler beim Schreiben
            }
    }

    fun readData(documentId: String, callback: (Any?) -> Unit) {
        db.collection("yourCollection")
            .document(documentId)
            .get()
            .addOnSuccessListener { documentSnapshot ->
                val data = documentSnapshot.toObject(Any::class.java)
                callback(data)
            }
            .addOnFailureListener { e ->
                // Fehler beim Lesen
                callback(null)
            }
    }

    fun updateData(documentId: String, newData: Map<String, Any>, callback: (Boolean) -> Unit) {
        db.collection("yourCollection")
            .document(documentId)
            .update(newData)
            .addOnSuccessListener {
                // Erfolgreich aktualisiert
                callback(true)
            }
            .addOnFailureListener { e ->
                // Fehler beim Aktualisieren
                callback(false)
            }
    }

    fun deleteData(documentId: String, callback: (Boolean) -> Unit) {
        db.collection("yourCollection")
            .document(documentId)
            .delete()
            .addOnSuccessListener {
                // Erfolgreich gelöscht
                callback(true)
            }
            .addOnFailureListener { e ->
                // Fehler beim Löschen
                callback(false)
            }
    }
}
