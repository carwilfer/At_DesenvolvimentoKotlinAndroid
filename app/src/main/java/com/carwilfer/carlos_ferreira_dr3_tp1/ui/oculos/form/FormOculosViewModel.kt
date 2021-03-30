package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import android.app.Application
import android.net.Uri
import android.util.Log
import androidx.core.net.toUri
import androidx.lifecycle.*
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.launch
import java.io.File

class FormOculosViewModel (
        private val oculosDao: OculosDao, application: Application
        ) : AndroidViewModel(application) {

    //private val oculos: OculosDao? = null
    private val app = application

    private val _imagemOculos = MutableLiveData<Uri>()
    val imagemOculos: LiveData<Uri> = _imagemOculos

    private val _oculos = MutableLiveData<Oculos>()
    val oculos: LiveData<Oculos> = _oculos

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        _status.value = false
        _msg.value = null
    }

    fun salvarOculos(armacaoId: String, /* dnpOlhoDireito: String, dnpOlhoEsquedo: String, alturaOlhoDireito: String, alturaOlhoEsquerdo: String, esfericoLongeOlhoDireito: String, esfericoLongeOlhoEsquedo: String, esfericoPertoOlhoDireito: String,
                     esfericoPertoOlhoEsquedo: String, cilindricoLongeOlhoDireito: String, cilindricoLongeOlhoEsquedo: String, cilindricoPertoOlhoDireito: String, cilindricoPertoOlhoEsquedo: String, eixoLongeOlhoDireito: String, eixoLongeOlhoEsquedo: String,
                     eixoPertoOlhoDireito: String, eixoPertoOlhoEsquedo: String,*/ lente: String, marcaArmacao: String, cor: String) {
        _status.value = false
        _msg.value = "Por favor, aguarde a persistencia!"

        val oculos = Oculos(armacaoId, /*dnpOlhoDireito, dnpOlhoEsquedo, alturaOlhoDireito, alturaOlhoEsquerdo, esfericoLongeOlhoDireito, esfericoLongeOlhoEsquedo, esfericoPertoOlhoDireito,
                esfericoPertoOlhoEsquedo, cilindricoLongeOlhoDireito, cilindricoLongeOlhoEsquedo, cilindricoPertoOlhoDireito, cilindricoPertoOlhoEsquedo, eixoLongeOlhoDireito, eixoLongeOlhoEsquedo,
                eixoPertoOlhoDireito, eixoPertoOlhoEsquedo,*/ lente, marcaArmacao, cor)

        uploadImageOculos(armacaoId)
        oculosDao.createOrUpdate(oculos)
            .addOnSuccessListener {
                _status.value = true
                _msg.value = "Persistência realizada!"
            }
            .addOnFailureListener {
                _msg.value = "Persistência falhou!"
                Log.e("OculosDaoFirebase", "${it.message}")
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

    fun setImagemOculos(photo: Uri) {
        _imagemOculos.value = photo
    }

    private fun getFileReference(armacaoId: String): StorageReference {
        val firebaseStorage = FirebaseStorage.getInstance()
        val storageReference = firebaseStorage.reference
        val fileReference = storageReference.child("Oculos/imagens/$armacaoId.png")
        return fileReference
    }

    fun uploadImageOculos(armacaoId: String) {
        getFileReference(armacaoId)
            .putFile(imagemOculos!!.value!!).addOnSuccessListener {
                _msg.value = "Imagem persistida com sucesso."
            }
            .addOnFailureListener {
                _msg.value = "Imagem não pode ser carregada: ${it.message}"
            }
    }
    fun setUpImagemOculos(armacaoId: String) {
        val file = File(app.cacheDir, "temp.png")
        getFileReference(armacaoId).getFile(file).addOnSuccessListener {
                setImagemOculos(file.toUri())
            }
            .addOnFailureListener {
                _msg.value = "Imagem não pode ser carregada: ${it.message}"
            }
    }

}