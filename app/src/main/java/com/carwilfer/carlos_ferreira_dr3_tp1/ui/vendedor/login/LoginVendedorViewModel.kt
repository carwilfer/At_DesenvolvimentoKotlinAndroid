package com.carwilfer.carlos_ferreira_dr3_tp1.ui.vendedor.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carwilfer.carlos_ferreira_dr3_tp1.database.VendedorFirebaseDao

class LoginVendedorViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun verificarCredenciais(email: String, senha: String) {
        VendedorFirebaseDao
                .verificarCredenciais(email, senha) // signInWithEmailAndPassword
                .addOnSuccessListener {
                    _status.value = true
                }
                .addOnFailureListener {
                    _msg.value = it.message
                }
    }
}