package com.carwilfer.carlos_ferreira_dr3_tp1.ui.cliente.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.carwilfer.carlos_ferreira_dr3_tp1.database.ClienteFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Cliente

class ListaClienteViewModel : ViewModel() {

    private val _clientes = MutableLiveData<List<Cliente>>()
    val clientes: LiveData<List<Cliente>> = _clientes

    init{
        ClienteFirestoreDao().all().addSnapshotListener{ snapshot, error ->
            if (error == null && snapshot != null && !snapshot.isEmpty)
                _clientes.value = snapshot.toObjects(Cliente::class.java)
        }
    }

    /*fun atualizarListaClientes(){
        Log.i("ListaClientesViewModel", "Atualizando Lista de Clientes")
        viewModelScope.launch {
            _clientes.value = RepositorioClientes.getInstance().all()
            Log.i("ListaClienteViewModel", "Clientes Atualizados.")
        }
        Log.i("ListaClienteViewModel", "Atualização Finalizada.")
    }*/
}