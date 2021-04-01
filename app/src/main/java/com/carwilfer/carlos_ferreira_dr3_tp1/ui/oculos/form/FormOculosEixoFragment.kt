package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.LogRegister
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import kotlinx.android.synthetic.main.form_oculos_cilindrico_fragment.*
import kotlinx.android.synthetic.main.form_oculos_fragment.*

class FormOculosEixoFragment : Fragment() {

    val application = requireActivity().application
    val formOculosViewModelFactory = FormOculosViewModelFactory(OculosFirestoreDao(), application)

    private lateinit var viewModelFormOculosEixo: FormOculosEixoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_oculos_eixo_fragment, container, false)
        LogRegister.getInstance(requireContext()).escreverLog("Acessou: FormOculosFragment")

        viewModelFormOculosEixo = ViewModelProvider(this).get(FormOculosEixoViewModel::class.java)
        viewModelFormOculosEixo.oculos.observe(viewLifecycleOwner, {
            if (it != null){
                preencherFormulario(it)
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        if (OculosEClienteUtil.oculosSelecionado != null){
            fabFormOculosComentarios.visibility = View.VISIBLE
            viewModelFormOculosEixo.selectOculos(OculosEClienteUtil.oculosSelecionado!!.armacaoId!!)
        }else{
            fabFormOculosComentarios.visibility = View.GONE
        }

        btnFormCilindricoProximo.setOnClickListener{
            LogRegister.getInstance(requireContext()).escreverLog("Cadastrar Óculos")

            val eixoLongeOlhoDireito = editTextCilindricoLongeOd.text.toString()
            val eixoLongeOlhoEsquedo = editTextCilindricoLongeOe.text.toString()
            val eixoPertoOlhoDireito = editTextCilindricoPertoOd.text.toString()
            val eixoPertoOlhoEsquedo = editTextCilindricoPertoEs.text.toString()


            viewModelFormOculosEixo.salvarOculosEixo(eixoLongeOlhoDireito,
                eixoLongeOlhoEsquedo, eixoPertoOlhoDireito, eixoPertoOlhoEsquedo
            )
        }

        fabFormOculosComentarios.setOnClickListener {
            findNavController().navigate(R.id.comentarioOculosFragment)
        }
    }

    private fun preencherFormulario(oculos: Oculos){

        editTextCilindricoLongeOd.setText(oculos.eixoLongeOlhoDireito)
        editTextCilindricoLongeOe.setText(oculos.eixoLongeOlhoEsquedo)
        editTextCilindricoPertoOd.setText(oculos.eixoPertoOlhoDireito)
        editTextCilindricoPertoEs.setText(oculos.eixoPertoOlhoEsquedo)
    }

}