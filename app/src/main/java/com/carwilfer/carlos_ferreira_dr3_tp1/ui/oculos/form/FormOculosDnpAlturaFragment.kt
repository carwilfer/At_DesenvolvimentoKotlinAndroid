package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.LogRegister
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import kotlinx.android.synthetic.main.form_oculos_dnp_altura_fragment.*

class FormOculosDnpAlturaFragment : Fragment() {

    private lateinit var FormOculosDnpAltura: FormOculosDnpAlturaViewModel
    private lateinit var oculosDao : OculosDao

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.form_oculos_dnp_altura_fragment, container, false)
        LogRegister.getInstance(requireContext()).escreverLog("Acessou: FormOculosFragment")
        oculosDao =  OculosFirestoreDao()

        val formOculosDnpAlturaViewModelFectory = FormOculosDnpAlturaViewModelFectory(oculosDao)
        FormOculosDnpAltura = ViewModelProvider(this, formOculosDnpAlturaViewModelFectory).get(FormOculosDnpAlturaViewModel::class.java)
        FormOculosDnpAltura.oculos.observe(viewLifecycleOwner, Observer{
            if (it != null){
                preencherFormulario(it)
            }
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        btnFormDnpAlturaProximo.setOnClickListener{
            LogRegister.getInstance(requireContext()).escreverLog("Cadastrar Óculos")

            val dnpOlhoDireito = editTextDnpOd.text.toString()
            val dnpOlhoEsquedo = editTextDnpOe.text.toString()
            val alturaOlhoDireito = editTextAlturaOd.text.toString()
            val alturaOlhoEsquerdo = editTextAlturaOe.text.toString()

            FormOculosDnpAltura.salvarOculosDnpAltura(dnpOlhoDireito, dnpOlhoEsquedo, alturaOlhoDireito, alturaOlhoEsquerdo
            )
            findNavController().navigate(R.id.formOculosEsfericoFragment3)
        }

    }
    private fun preencherFormulario(oculos: Oculos){
        editTextDnpOd.setText(oculos.dnpOlhoDireito)
        editTextDnpOe.setText(oculos.dnpOlhoEsquedo)
        editTextAlturaOd.setText(oculos.alturaOlhoDireito)
        editTextAlturaOe.setText(oculos.alturaOlhoEsquedo)
    }
}