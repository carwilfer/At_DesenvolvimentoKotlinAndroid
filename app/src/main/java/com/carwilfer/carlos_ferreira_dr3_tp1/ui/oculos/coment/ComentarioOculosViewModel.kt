package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.coment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carwilfer.carlos_ferreira_dr3_tp1.database.ComentarioFirebaseDao
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Comentario

class ComentarioOculosViewModel : ViewModel() {
    private val _comentarios = MutableLiveData<List<Comentario>>()
    val comentarios: LiveData<List<Comentario>> = _comentarios

    init {
        ComentarioFirebaseDao(OculosEClienteUtil!!.oculosSelecionado!!.armacaoId!!)
            .all()
            .addSnapshotListener { snapshot, error ->
                if (error == null && snapshot != null && !snapshot.isEmpty)
                    _comentarios.value = snapshot.toObjects(Comentario::class.java)
            }
    }

    fun save(texto: String) {
        ComentarioFirebaseDao(OculosEClienteUtil!!.oculosSelecionado!!.armacaoId!!)
            .create(Comentario(texto))
    }
}