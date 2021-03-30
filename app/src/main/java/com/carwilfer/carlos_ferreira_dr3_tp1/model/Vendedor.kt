package com.carwilfer.carlos_ferreira_dr3_tp1.model

import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.DocumentId

class Vendedor (
    var nome: String? = null,
    var telefone: String? = null,
    var otica: String? = null,
    val cnpj: Long? = null,
    var firebaseUser: FirebaseUser? = null,
    @DocumentId
    var uid: String? = null
)