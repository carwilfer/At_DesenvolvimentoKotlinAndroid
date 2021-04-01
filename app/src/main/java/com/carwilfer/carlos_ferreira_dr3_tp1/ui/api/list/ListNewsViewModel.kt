package com.carwilfer.carlos_ferreira_dr3_tp1.ui.api.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carwilfer.carlos_ferreira_dr3_tp1.api.ApiClient
import com.carwilfer.carlos_ferreira_dr3_tp1.model.News
import com.carwilfer.carlos_ferreira_dr3_tp1.model.ResponseTypes
import kotlinx.coroutines.launch

class ListNewsViewModel : ViewModel() {

    private val _responseTypes = MutableLiveData<List<ResponseTypes>>()
    val responseTypes: LiveData<List<ResponseTypes>> = _responseTypes

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    /*init {
        viewModelScope.launch {
            try {
                val responseTypes = ApiClient.getNewsService()
                _news.value = responseTypes.all()
            }catch (e: Exception){
                _msg.value = e.message
            }

        }
    }*/
}