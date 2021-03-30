package com.carwilfer.carlos_ferreira_dr3_tp1.ui.vendedor.cadastro

import android.graphics.Bitmap
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carwilfer.carlos_ferreira_dr3_tp1.database.VendedorFirebaseDao

class CadastroVendedorViewModel : ViewModel() {

    private var imagemPerfil: Bitmap? = null

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun salvarCadastroVendedor(email: String, senha: String,
                       nome: String, telefone: String) {
        VendedorFirebaseDao
                .cadastrarCredenciais(email, senha) // createUserWithEmailAndPassword
                .addOnSuccessListener {
                    val uid = it.user!!.uid         // firebaseUser.uid
                    VendedorFirebaseDao
                            .cadastrarPerfil(uid, nome, telefone)   // collection("usuarios").document(uid)
                            .addOnSuccessListener {
                                VendedorFirebaseDao
                                        .cadastrarImagem(uid, imagemPerfil!!)
                                        .addOnSuccessListener {
                                            _status.value = true
                                            _msg.value = "Usuário cadastrado com sucesso!"
                                        }
                                        .addOnFailureListener {
                                            _msg.value = "${it.message}"
                                        }
                            }
                            .addOnFailureListener {
                                _msg.value = "${it.message}"
                            }
                }
                .addOnFailureListener {
                    _msg.value = "${it.message}"
                }
    }

    fun alterarImagemPerfilVendedor(imagem: Bitmap){
        imagemPerfil = imagem
    }

}