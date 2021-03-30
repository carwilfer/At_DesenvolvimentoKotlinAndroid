package com.carwilfer.carlos_ferreira_dr3_tp1.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.DocumentReference

@Entity
class Cliente (
    val nome: String? = null,
    val endereco: String? = null,
    val telefone: String? = null,

    @DocumentId
    var cpf: String? = null,
    var uid: String? = null,
    var oculos: DocumentReference? = null,


/*    @PrimaryKey(autoGenerate = true)
    val id: Long? = null*/
){
    override fun toString(): String = "$nome:  (Tel.: $telefone)"

}