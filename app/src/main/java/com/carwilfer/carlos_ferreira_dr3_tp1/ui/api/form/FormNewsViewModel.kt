package com.carwilfer.carlos_ferreira_dr3_tp1.ui.api.form

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carwilfer.carlos_ferreira_dr3_tp1.api.ApiClient
import com.carwilfer.carlos_ferreira_dr3_tp1.model.News
import com.carwilfer.carlos_ferreira_dr3_tp1.model.ResponseTypes
import kotlinx.coroutines.launch
import java.lang.Exception

class FormNewsViewModel : ViewModel() {

    private val _status = MutableLiveData<Boolean>()
    val status: LiveData<Boolean> = _status

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    fun save(assunto: String, conteudo: String) {
        try {
            val responseTypes = ResponseTypes(assunto, conteudo)
            viewModelScope.launch {
                ApiClient.getNewsService().insert(responseTypes)
                _status.value = true
                _msg.value = "Notícia salva."
            }

        } catch (e: Exception){
            _msg.value = e.message
        }
    }
    // TODO: Implement the ViewModel
}