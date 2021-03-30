package com.carwilfer.carlos_ferreira_dr3_tp1

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form.FormOculosViewModelFactory
import kotlinx.android.synthetic.main.form_oculos_dnp_altura_fragment.*
import kotlinx.android.synthetic.main.form_oculos_eixo_fragment.*
import kotlinx.android.synthetic.main.form_oculos_fragment.*

class FormOculosDnpAlturaFragment : Fragment() {

    val application = requireActivity().application
    val formOculosViewModelFactory = FormOculosViewModelFactory(OculosFirestoreDao(), application)

        private lateinit var FormOculosDnpAltura: FormOculosDnpAlturaViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.form_oculos_dnp_altura_fragment, container, false)
        LogRegister.getInstance(requireContext()).escreverLog("Acessou: FormOculosFragment")

        FormOculosDnpAltura = ViewModelProvider(this).get(FormOculosDnpAlturaViewModel::class.java)
        FormOculosDnpAltura.oculos.observe(viewLifecycleOwner, {
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
            FormOculosDnpAltura.selectOculos(OculosEClienteUtil.oculosSelecionado!!.armacaoId!!)
        }else{
            fabFormOculosComentarios.visibility = View.GONE
        }

        btnFormDnpAlturaProximo.setOnClickListener{
            LogRegister.getInstance(requireContext()).escreverLog("Cadastrar Óculos")

            val dnpOlhoDireito = editTextDnpOd.text.toString()
            val dnpOlhoEsquedo = editTextDnpOe.text.toString()
            val alturaOlhoDireito = editTextAlturaOd.text.toString()
            val alturaOlhoEsquerdo = editTextAlturaOe.text.toString()

            FormOculosDnpAltura.salvarOculosDnpAltura(dnpOlhoDireito, dnpOlhoEsquedo, alturaOlhoDireito, alturaOlhoEsquerdo
            )
            findNavController().navigate(R.id.formOculosFragment3)
        }


        /*fabFormOculosComentarios.setOnClickListener {
            findNavController().navigate(R.id.formOculosFragment2)
        }*/
    }
    private fun preencherFormulario(oculos: Oculos){
        editTextDnpOd.setText(oculos.dnpOlhoDireito)
        editTextDnpOe.setText(oculos.dnpOlhoEsquedo)
        editTextAlturaOd.setText(oculos.alturaOlhoDireito)
        editTextAlturaOe.setText(oculos.alturaOlhoEsquedo)
    }
}