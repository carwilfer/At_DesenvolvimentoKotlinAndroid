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
import kotlinx.android.synthetic.main.form_oculos_esferico_fragment.*
import kotlinx.android.synthetic.main.form_oculos_fragment.*

class FormOculosEsfericoFragment : Fragment() {

    val application = requireActivity().application
    val formOculosViewModelFactory = FormOculosViewModelFactory(OculosFirestoreDao(), application)

    private lateinit var viewModelFormOculosEsferico: FormOculosEsfericoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_oculos_esferico_fragment, container, false)
        LogRegister.getInstance(requireContext()).escreverLog("Acessou: FormOculosFragment")

        viewModelFormOculosEsferico = ViewModelProvider(this).get(FormOculosEsfericoViewModel::class.java)
        viewModelFormOculosEsferico.oculos.observe(viewLifecycleOwner, {
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
            viewModelFormOculosEsferico.selectOculos(OculosEClienteUtil.oculosSelecionado!!.armacaoId!!)
        }else{
            fabFormOculosComentarios.visibility = View.GONE
        }

        btnFormEsfericoProximo.setOnClickListener{
            LogRegister.getInstance(requireContext()).escreverLog("Cadastrar Óculos")

            val esfericoLongeOlhoDireito = editTextEsfericoLongeOd.text.toString()
            val esfericoLongeOlhoEsquedo = editTextEsfericoLongeEs.text.toString()
            val esfericoPertoOlhoDireito = editTextEsfericoPertoOd.text.toString()
            val esfericoPertoOlhoEsquedo = editTextEsfericoPertoEs.text.toString()


            viewModelFormOculosEsferico.salvarOculosEsferico(esfericoLongeOlhoDireito, esfericoLongeOlhoEsquedo, esfericoPertoOlhoDireito, esfericoPertoOlhoEsquedo
            )
            findNavController().navigate(R.id.formOculosFragment4)
        }

        /*fabFormOculosComentarios.setOnClickListener {
            findNavController().navigate(R.id.formOculosFragment3)
        }*/
    }
    private fun preencherFormulario(oculos: Oculos){

        editTextEsfericoLongeOd.setText(oculos.esfericoLongeOlhoDireito)
        editTextEsfericoLongeEs.setText(oculos.esfericoLongeOlhoEsquedo)
        editTextEsfericoPertoOd.setText(oculos.esfericoPertoOlhoDireito)
        editTextEsfericoPertoEs.setText(oculos.esfericoPertoOlhoEsquedo)
    }
}