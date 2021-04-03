package com.carwilfer.carlos_ferreira_dr3_tp1.ui.api.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carwilfer.carlos_ferreira_dr3_tp1.api.ApiClient
import com.carwilfer.carlos_ferreira_dr3_tp1.model.News
import kotlinx.coroutines.launch

class ListNewsViewModel : ViewModel() {

    /*private val _responseTypes = MutableLiveData<List<ResponseTypes>>()
    val responseTypes: LiveData<List<ResponseTypes>> = _responseTypes

    private val _responseNews = MutableLiveData<List<ResponseNews>>()
    val responseNews: LiveData<List<ResponseNews>> = _responseNews*/

    private val _news = MutableLiveData<List<News>>()
    val news: LiveData<List<News>> = _news

    private val _msg = MutableLiveData<String>()
    val msg: LiveData<String> = _msg

    init {
        viewModelScope.launch {
            try {
                val responseTypes =
                        ApiClient
                                .getNewsService().all()
                val response = responseTypes.response
                _news.value = response!!.docs!!
            }catch (e: Exception){
                Log.i("LCVWResponse", "${e.message}")
            }

        }
    }

    /*init {
        viewModelScope.launch {
            try {
                val responseTypes = ApiClient.getNewsService()
                _responseTypes.value = responseTypes.all()
            }catch (e: Exception){
                _msg.value = e.message
            }

        }
    }*/
}