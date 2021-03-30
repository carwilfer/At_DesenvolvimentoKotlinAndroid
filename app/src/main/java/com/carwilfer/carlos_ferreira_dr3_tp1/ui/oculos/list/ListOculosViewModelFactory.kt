package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import java.lang.IllegalArgumentException

class ListOculosViewModelFactory (
        private val oculosDao: OculosDao
        ) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ListaOculosViewModel::class.java))
        return ListaOculosViewModel(oculosDao) as T
        throw IllegalArgumentException("Classe viewModel desconhecida.")
    }
}