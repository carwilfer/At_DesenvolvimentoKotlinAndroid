package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import java.lang.IllegalArgumentException

class FormOculosDnpAlturaViewModelFectory (
        val oculosDao: OculosDao
) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FormOculosDnpAlturaViewModel::class.java)){
            return FormOculosDnpAlturaViewModel(oculosDao) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}