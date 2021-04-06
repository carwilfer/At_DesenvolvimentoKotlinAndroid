package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import com.carwilfer.carlos_ferreira_dr3_tp1.model.OculosForm

class FormOculosCilindricoViewModel (
    private val oculosDao: OculosDao
) : ViewModel() {

    private val _oculos = MutableLiveData<Oculos>()
    val oculos: LiveData<Oculos> = _oculos

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    /*init {
        _status.value = false
        _msg.value = null
    }*/

    fun salvarOculosCilindrico(cilindricoLongeOlhoDireito: String, cilindricoLongeOlhoEsquedo: String, cilindricoPertoOlhoDireito: String, cilindricoPertoOlhoEsquedo: String){
        _status.value = false
        _msg.value = "Por favor, aguarde a persistencia!"

        val armacaoId = OculosForm.armacaoId
        if (armacaoId != null){
        val oculos = Oculos(armacaoId = armacaoId,
                cilindricoLongeOlhoDireito = cilindricoLongeOlhoDireito,
                cilindricoLongeOlhoEsquedo = cilindricoLongeOlhoEsquedo,
                cilindricoPertoOlhoDireito = cilindricoPertoOlhoDireito,
                cilindricoPertoOlhoEsquedo = cilindricoPertoOlhoEsquedo)
        val task = oculosDao.createOrUpdate(oculos)

        task.addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada!"
            }
            .addOnFailureListener {
                _msg.value = "Persistência falhou!"
                Log.e("OculosDaoFirebase", "${it.message}")
            }
        }else {
            _msg.value = "Armacao nao encontrada!"
        }

    }

    fun selectOculos(armacaoId: String) {
        oculosDao.read(armacaoId)
            .addOnSuccessListener {
                val oculos = it.toObject(Oculos::class.java)
                if (oculos != null)
                    _oculos.value = oculos!!
                else
                    _msg.value = "O óculos foi encontrado."
            }
            .addOnFailureListener {
                _msg.value = "${it.message}"
            }
    }
}