package com.carwilfer.carlos_ferreira_dr3_tp1.model

import androidx.room.PrimaryKey
import com.google.firebase.firestore.DocumentId

open class OculosLite (
        val marcaArmacao: String? = null,
        val lente: String? = null,
        val cor: String? = null,

        @DocumentId
    val armacaoId: String? = null,

        ){
    override fun toString(): String = "$marcaArmacao: $lente: $cor"
}