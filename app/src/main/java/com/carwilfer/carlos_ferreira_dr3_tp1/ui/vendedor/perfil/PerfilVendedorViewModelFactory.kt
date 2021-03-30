package com.carwilfer.carlos_ferreira_dr3_tp1.ui.vendedor.perfil

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PerfilVendedorViewModelFactory(
    val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PerfilVendedorViewModel::class.java)){
            return PerfilVendedorViewModel(application) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }

}
