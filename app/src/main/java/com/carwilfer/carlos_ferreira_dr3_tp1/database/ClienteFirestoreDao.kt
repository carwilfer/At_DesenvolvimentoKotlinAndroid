package com.carwilfer.carlos_ferreira_dr3_tp1.database

import com.carwilfer.carlos_ferreira_dr3_tp1.model.Cliente
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot

class ClienteFirestoreDao {
    private val collection = FirebaseFirestore
        .getInstance()
        .collection("cliente")

    fun createOrUpdate(cliente: Cliente): Task<Void> {
        return collection
            .document(cliente.cpf!!)
            .set(cliente)
    }

    fun all(): CollectionReference {
        return collection
    }

    fun read(id: String): Task<DocumentSnapshot> {
        return collection.document(id).get()
    }

    fun searchByNome(nome: String): Task<QuerySnapshot>{
        return collection
            .whereEqualTo("NomeCliente", nome).get()
    }

    fun allDocuments(): Task<QuerySnapshot> {
        return collection.get()
    }

    fun delete(cliente: Cliente): Task<Void> {
        return collection.document(cliente.cpf!!).delete()
    }
}