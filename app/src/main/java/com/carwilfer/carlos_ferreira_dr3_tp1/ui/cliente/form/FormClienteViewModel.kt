package com.carwilfer.carlos_ferreira_dr3_tp1.ui.cliente.form

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carwilfer.carlos_ferreira_dr3_tp1.database.ClienteFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Cliente
import com.google.firebase.firestore.DocumentId
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.launch

class FormClienteViewModel : ViewModel() {

    private val _oculos = MutableLiveData<List<String>>()
    val oculos: LiveData<List<String>> = _oculos

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    private var armacaoOculos: String? = null
    private var imagemPerfil: Bitmap? = null

    init {
        OculosFirestoreDao().allDocuments().addOnSuccessListener{
            val listaOculos = mutableListOf<String>("Selecione um óculos")
            it.documents.forEach{
                listaOculos.add(it.id)
            }
            _oculos.value = listaOculos
        }
    }

    fun salvarCliente(nome: String,
                      endereco: String, telefone: String,
                      cpf: String){
        val cliente = Cliente(nome, endereco, telefone, cpf)

        cliente.oculos = FirebaseFirestore
                .getInstance()
                .collection("óculos")
                .document(armacaoOculos!!)

        ClienteFirestoreDao()
                .createOrUpdate(cliente)
                .addOnSuccessListener {
                    _status.value = true
                    _msg.value = "Cliente salvo com sucesso."
                }
                .addOnFailureListener {
                    _msg.value = "${it.message}"
                }
    }

    fun definirArmacaoOculosSelecionada(armacaoId: String) {
        armacaoOculos = armacaoId
    }

    fun alterarImagemPerfil(imagem: Bitmap){
        imagemPerfil = imagem
    }


    /*fun salvarCliente(nome: String, cpf: String) {
        val cliente = Cliente(nome, cpf)
        viewModelScope.launch{
            RepositorioClientes.getInstance().store(cliente)
        }
    }*/
}