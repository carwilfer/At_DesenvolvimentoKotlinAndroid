package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.config

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
import kotlinx.android.synthetic.main.config_oculos_fragment.*
import java.io.File

class ConfigOculosFragment : Fragment() {

    companion object {
        fun newInstance() = ConfigOculosFragment()
    }

    private lateinit var viewModel: ConfigOculosViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.config_oculos_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ConfigOculosViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val textoOculos = LogRegister.getInstance(requireContext()).lerLog()
        textViewLabelConteudoOculos.text = textoOculos.toString()

        btnVerificarArquivoOculos.setOnClickListener{
            val nomeArquivoOculos = editTextArquivoNomeOculos.text.toString()
            val file = File(requireActivity().filesDir, nomeArquivoOculos)
            if(file.exists()) {
                textViewVerificaExisteOculos.text = "Sim"
                verificarLeituraEscritaOculos(file)
            }
            else{
                textViewVerificaExisteOculos.text = "Não"
                textViewVerificaLegivelOculos.text = "Não"
                textViewVerificaEditavelOculos.text = "Não"
                btnCriarArquivoOculos.isEnabled = true
            }

        }
        btnCriarArquivoOculos.setOnClickListener{
            val nomeArquivoOculos = editTextArquivoNomeOculos.text.toString()
            val file = File(requireActivity().filesDir, nomeArquivoOculos)
            file.createNewFile()
        }
    }

    private fun verificarLeituraEscritaOculos(file: File) {
        if (file.canRead())
            textViewVerificaLegivelOculos.text = "Sim"
        else
            textViewVerificaLegivelOculos.text = "Não"
        if (file.canWrite()) {
            textViewVerificaEditavelOculos.text = "Sim"
            escreverEmArquivoOculos(file.name, "Teste de escrita em oculos.\n")
        }else
            textViewVerificaEditavelOculos.text = "Não"
    }

    private fun escreverEmArquivoOculos(nome: String, msg: String){
        //FileOutputStream - openFileOutput(nome, modo)
        val fileOutputStreamOculos = requireActivity()
            .openFileOutput(nome, Context.MODE_APPEND)
        fileOutputStreamOculos.write(msg.toByteArray())
    }
}