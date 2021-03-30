package com.carwilfer.carlos_ferreira_dr3_tp1.ui.cliente.show

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import kotlinx.android.synthetic.main.show_cliente_fragment.*

class ShowClienteFragment : Fragment() {

    private lateinit var viewModelShowCliente: ShowClienteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelShowCliente = ViewModelProvider(this).get(ShowClienteViewModel::class.java)
        return inflater.inflate(R.layout.show_cliente_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val cliente = OculosEClienteUtil.clienteSelecionado!!

        textViewShowClienteNome.text = cliente.nome
        cliente.oculos!!.get()
            .addOnSuccessListener {
                val oculos = it.toObject(Oculos::class.java)
                textViewShowClienteOculosArmacaoId.text = oculos!!.armacaoId
            }
            .addOnFailureListener {
                Log.i("ShowClienteFragment", "${it.message}")
            }
    }

}