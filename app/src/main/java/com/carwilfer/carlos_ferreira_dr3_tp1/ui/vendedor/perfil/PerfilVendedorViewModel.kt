package com.carwilfer.carlos_ferreira_dr3_tp1.ui.vendedor.perfil

import android.app.Application
import android.net.Uri
import androidx.core.net.toUri
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carwilfer.carlos_ferreira_dr3_tp1.database.VendedorFirebaseDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Vendedor
import java.io.File

class PerfilVendedorViewModel(
    application: Application
) : AndroidViewModel(application) {
    val app = application

    private val _imagemPerfilVendedor = MutableLiveData<Uri>()
    val imagemPerfilVendedor: LiveData<Uri> = _imagemPerfilVendedor

    private val _vendedor = MutableLiveData<Vendedor?>()
    val vendedor: LiveData<Vendedor?> = _vendedor

    init {
        VendedorFirebaseDao
            .consultarVendedores()
            .addOnSuccessListener {
                val vendedor = it.toObject(Vendedor::class.java)
                vendedor!!.firebaseUser = VendedorFirebaseDao.firebaseAuth.currentUser
                _vendedor.value = vendedor!!
                val file = File(app.cacheDir, "userTemp.jpeg")
                VendedorFirebaseDao
                    .consultarImagem(vendedor!!.uid!!, file)
                    .addOnSuccessListener {
                        _imagemPerfilVendedor.value = file.toUri()
                    }
            }
    }

    fun encerrarSessao() {
        VendedorFirebaseDao.encerrarSessao()
        _vendedor.value = null
    }

}