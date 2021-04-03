package com.carwilfer.carlos_ferreira_dr3_tp1.model

import com.google.firebase.firestore.DocumentId

open class Oculos (
    armacaoId: String? = null,
    val dnpOlhoDireito: String? = null,
    val dnpOlhoEsquedo: String? = null,
    val alturaOlhoDireito: String? = null,
    val alturaOlhoEsquedo: String? = null,
    val esfericoLongeOlhoDireito: String? = null,
    val esfericoLongeOlhoEsquedo: String? = null,
    val esfericoPertoOlhoDireito: String? = null,
    val esfericoPertoOlhoEsquedo: String? = null,
    val cilindricoLongeOlhoDireito: String? = null,
    val cilindricoLongeOlhoEsquedo: String? = null,
    val cilindricoPertoOlhoDireito: String? = null,
    val cilindricoPertoOlhoEsquedo: String? = null,
    val eixoLongeOlhoDireito: String? = null,
    val eixoLongeOlhoEsquedo: String? = null,
    val eixoPertoOlhoDireito: String? = null,
    val eixoPertoOlhoEsquedo: String? = null,
    lente: String? = null,
    marcaArmacao: String? = null,
    cor: String? = null,
    @DocumentId
    var uid: String? = null
): OculosLite(marcaArmacao, cor, lente, armacaoId){
    override fun toString(): String = "$marcaArmacao: $lente: $cor"
}