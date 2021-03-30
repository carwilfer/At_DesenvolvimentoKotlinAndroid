package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import java.lang.IllegalArgumentException

class FormOculosViewModelFactory (
    val oculosDao: OculosDao,
    val application: Application
        ) : ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(FormOculosViewModel::class.java)){
            return FormOculosViewModel(oculosDao, application) as T
        }
        throw IllegalArgumentException("Classe ViewModel desconhecida.")
    }
}