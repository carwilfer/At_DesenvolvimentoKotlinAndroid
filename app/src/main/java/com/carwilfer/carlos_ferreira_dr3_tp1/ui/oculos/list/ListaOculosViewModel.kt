package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import com.carwilfer.carlos_ferreira_dr3_tp1.model.OculosLite
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ListaOculosViewModel(
    private val oculosDao: OculosDao
) : ViewModel() {
    private val _oculos = MutableLiveData<List<OculosLite>>()
    val oculos: LiveData<List<OculosLite>> = _oculos

    fun atualizarListaOculos() {

        oculosDao.all().addSnapshotListener { snapshot, error ->
            if (error != null)
                Log.i("ListaOculosViewModel", "Atualizando Lista de Oculos")
            else
                if (snapshot != null && !snapshot.isEmpty) {
                    val oculos = snapshot.toObjects(OculosLite::class.java)
                    _oculos.value = oculos
                }
            /*viewModelScope.launch {
            _oculos.value = RepositorioOculos.getInstance().all()
            Log.i("ListaOculosViewModel", "Oculos Atualizados.")
        }
        Log.i("ListaOculosViewModel", "Atualização Finalizada.")*/
        }
    }
}