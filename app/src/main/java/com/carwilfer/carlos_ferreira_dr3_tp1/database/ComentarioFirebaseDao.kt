package com.carwilfer.carlos_ferreira_dr3_tp1.database

import com.carwilfer.carlos_ferreira_dr3_tp1.model.Comentario
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore

class ComentarioFirebaseDao(id: String) {
    private val collection =
            FirebaseFirestore.getInstance().collection("oculos").document(id).collection("comentarios")

    fun create(comentario: Comentario): Task<DocumentReference> {
        return collection.add(comentario)
    }

    fun all(): CollectionReference {
        return collection
    }
}