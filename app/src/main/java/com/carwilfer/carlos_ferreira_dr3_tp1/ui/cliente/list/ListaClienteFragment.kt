package com.carwilfer.carlos_ferreira_dr3_tp1.ui.cliente.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import kotlinx.android.synthetic.main.lista_cliente_fragment.*

class ListaClienteFragment : Fragment() {

    private lateinit var viewModelListaCliente: ListaClienteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view = inflater.inflate(R.layout.lista_cliente_fragment, container, false)
        viewModelListaCliente = ViewModelProvider(this).get(ListaClienteViewModel::class.java)
        viewModelListaCliente.clientes.observe(viewLifecycleOwner){
            listViewCliente.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                it
            )
        }
        return view

       /* viewModelListaCliente.clientes.observe(viewLifecycleOwner, {
        if (!it.isNullOrEmpty())
            listViewCliente.adapter =
                ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    it
                )
        listViewCliente.setOnItemClickListener { parent, view, position, id ->
            val cliente = it[position]
            OculosEClienteUtil.clienteSelecionado = cliente
            findNavController().navigate(R.id.showClienteFragment)
        }
    })
        return view*/

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fabFormCliente.setOnClickListener{
            findNavController().navigate(R.id.formClienteFragment)
        }

    }
}