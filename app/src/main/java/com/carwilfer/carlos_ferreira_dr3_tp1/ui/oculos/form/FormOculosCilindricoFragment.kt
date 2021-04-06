package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.LogRegister
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import kotlinx.android.synthetic.main.form_oculos_cilindrico_fragment.*
import kotlinx.android.synthetic.main.form_oculos_fragment.*

class FormOculosCilindricoFragment : Fragment() {

    private lateinit var viewModelFormOculosCilindrico: FormOculosCilindricoViewModel
    private lateinit var oculosDao : OculosDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_oculos_cilindrico_fragment, container, false)
        LogRegister.getInstance(requireContext()).escreverLog("Acessou: FormOculosFragment")

        oculosDao =  OculosFirestoreDao()
        val formOculosCilindricoViewModelFectory = FormOculosCilindricoViewModelFactory(oculosDao)

        viewModelFormOculosCilindrico = ViewModelProvider(this, formOculosCilindricoViewModelFectory).get(FormOculosCilindricoViewModel::class.java)

        viewModelFormOculosCilindrico.oculos.observe(viewLifecycleOwner, Observer{
            if (it != null){
                preencherFormulario(it)
            }
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        btnFormCilindricoProximo.setOnClickListener{
            LogRegister.getInstance(requireContext()).escreverLog("Cadastrar Óculos")

            val cilindricoLongeOlhoDireito = editTextCilindricoLongeOd.text.toString()
            val cilindricoLongeOlhoEsquedo = editTextCilindricoLongeOe.text.toString()
            val cilindricoPertoOlhoDireito = editTextCilindricoPertoOd.text.toString()
            val cilindricoPertoOlhoEsquedo = editTextCilindricoPertoEs.text.toString()


            viewModelFormOculosCilindrico.salvarOculosCilindrico(cilindricoLongeOlhoDireito,
                cilindricoLongeOlhoEsquedo, cilindricoPertoOlhoDireito, cilindricoPertoOlhoEsquedo
            )
            findNavController().navigate(R.id.formOculosEixoFragment5)
        }
    }

    private fun preencherFormulario(oculos: Oculos){

        editTextCilindricoLongeOd.setText(oculos.cilindricoLongeOlhoDireito)
        editTextCilindricoLongeOe.setText(oculos.cilindricoLongeOlhoEsquedo)
        editTextCilindricoPertoOd.setText(oculos.cilindricoPertoOlhoDireito)
        editTextCilindricoPertoEs.setText(oculos.cilindricoPertoOlhoEsquedo)
    }

}