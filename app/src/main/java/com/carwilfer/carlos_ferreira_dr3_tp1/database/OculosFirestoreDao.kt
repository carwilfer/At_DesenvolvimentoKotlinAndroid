package com.carwilfer.carlos_ferreira_dr3_tp1.database

import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import com.carwilfer.carlos_ferreira_dr3_tp1.model.OculosAndCliente
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*

class OculosFirestoreDao: OculosDao {
    private val collection = FirebaseFirestore.getInstance().collection("oculos")

    override fun createOrUpdate(oculos: Oculos): Task<Void> {
        //if(oculos.armacaoId != null)
        return collection.document(oculos.armacaoId!!).set(oculos, SetOptions.merge())
       // else
            //return null
    }

    override fun all(): CollectionReference {
        return collection
    }

    override fun allDocuments(): Task<QuerySnapshot> {
        return collection.get()
    }

    override fun insert(oculos: Oculos): Long {
        TODO("Not yet implemented")
    }

    override fun delete(oculos: Oculos): Task<Void> {
        return collection.document(oculos.armacaoId!!).delete()
    }

    override fun read(armacaoId: String): Task<DocumentSnapshot> {
        return collection.document(armacaoId).get()
    }

    override fun searchByArmacao(marcaArmacao: String): Task<QuerySnapshot>{
        return collection
            .whereEqualTo("marcaArmacao", marcaArmacao).get()
    }
}