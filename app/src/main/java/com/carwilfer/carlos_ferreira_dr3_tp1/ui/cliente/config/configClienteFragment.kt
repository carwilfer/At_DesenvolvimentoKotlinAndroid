package com.carwilfer.carlos_ferreira_dr3_tp1.ui.cliente.config

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.carwilfer.carlos_ferreira_dr3_tp1.LogRegister
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import kotlinx.android.synthetic.main.config_cliente_fragment.*
import java.io.File

class configClienteFragment : Fragment() {

    companion object {
        fun newInstance() = configClienteFragment()
    }

    private lateinit var viewModel: ConfigClienteViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.config_cliente_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfigClienteViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textoCliente = LogRegister.getInstance(requireContext()).lerLog()
        textViewLabelConteudoCliente.text = textoCliente.toString()

        btnVerificarArquivoCliente.setOnClickListener{
            //armazenamento intermo e permanente
            val nomeArquivoCliente = editTextArquivoNomeCliente.text.toString()

            val file = File(requireActivity().filesDir, nomeArquivoCliente)
            if(file.exists()) {
                textViewVerificaExisteCliente.text = "Sim"
                verificarLeituraEscritaCliente(file)
            }
            else{
                textViewVerificaExisteCliente.text = "Não"
                textViewVerificaLegivelCliente.text = "Não"
                textViewVerificaEditavelCliente.text = "Não"
                btnCriarArquivoCliente.isEnabled = true
            }

        }
        btnCriarArquivoCliente.setOnClickListener{
            val nomeArquivoCliente = editTextArquivoNomeCliente.text.toString()
            val file = File(requireActivity().filesDir, nomeArquivoCliente)
            file.createNewFile()
        }
    }

    private fun verificarLeituraEscritaCliente(file: File) {
        if (file.canRead())
            textViewVerificaLegivelCliente.text = "Sim"
        else
            textViewVerificaLegivelCliente.text = "Não"
        if (file.canWrite()) {
            textViewVerificaEditavelCliente.text = "Sim"
            escreverEmArquivoCliente(file.name, "Teste de escrita em cliente.\n")
        }else
            textViewVerificaEditavelCliente.text = "Não"
    }

    private fun escreverEmArquivoCliente(nome: String, msg: String){
        //FileOutputStream - openFileOutput(nome, modo)
        val fileOutputStreamCliente = requireActivity()
            .openFileOutput(nome, Context.MODE_APPEND)
        fileOutputStreamCliente.write(msg.toByteArray())
    }

    private fun lerArquivocliente(nome: String){
        //FileInputStream - openFileInput(nome)
        val fileInputStream = requireActivity().openFileInput(nome)
        val textoCliente = fileInputStream.bufferedReader().readText()
        textViewLabelConteudoCliente.text = textoCliente
    }
}