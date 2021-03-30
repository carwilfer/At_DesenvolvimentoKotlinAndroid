package com.carwilfer.carlos_ferreira_dr3_tp1.model

import androidx.room.Embedded
import androidx.room.Relation

class OculosAndCliente (
        @Embedded val oculos: Oculos,

        @Relation(
                parentColumn = "id",
                entityColumn = "oculosId"

        )
        val cliente: Cliente
) {
        override fun toString(): String = "Oculos:" +
                "${oculos.marcaArmacao}\t" +
                "${oculos.cor}\t" +
                "${oculos.lente}\t" +
                "${cliente.cpf}\t" +
                "${cliente.nome}"
}

/* //Relacao 1 pra muitos
class OculosAndCliente (
        @Embedded val cliente: Cliente,
        @Relation(
                parentColumn = "id",
                entityColumn = "clienteCreatorId"
        )
        val oculos: List<Oculos>
)*/
