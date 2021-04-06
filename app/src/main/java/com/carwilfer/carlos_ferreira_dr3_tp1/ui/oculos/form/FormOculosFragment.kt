package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.LogRegister
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosDao
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Oculos
import com.carwilfer.carlos_ferreira_dr3_tp1.ui.cliente.list.ListaClienteViewModel
import kotlinx.android.synthetic.main.form_oculos_eixo_fragment.*
import kotlinx.android.synthetic.main.form_oculos_fragment.*
import kotlinx.android.synthetic.main.lista_oculos_fragment.*

class FormOculosFragment : Fragment() {

    private lateinit var viewModelFormOculos: FormOculosViewModel
    private lateinit var formViewModelCliente: ListaClienteViewModel

    private lateinit var oculosDao : OculosDao

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    {
        val view = inflater.inflate(R.layout.form_oculos_fragment, container, false)
        oculosDao =  OculosFirestoreDao()

        LogRegister.getInstance(requireContext()).escreverLog("Acessou: FormOculosFragment")

        val application = requireActivity().application
        val formOculosViewModelFactory = FormOculosViewModelFactory(oculosDao, application)
        viewModelFormOculos = ViewModelProvider(this, formOculosViewModelFactory).get(FormOculosViewModel::class.java).apply {
            setUpMsgObserver(this)
            setUpStatusObserver(this)
            setUpImagemOculosObserver(this)
        }
        formViewModelCliente = ViewModelProvider(this).get(ListaClienteViewModel::class.java)

        formViewModelCliente.clientes.observe(viewLifecycleOwner, Observer{
            var adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerListaCliente.adapter = adapter
        })
        //return view

        viewModelFormOculos.oculos.observe(viewLifecycleOwner, Observer{
            if (it != null){
                preencherFormulario(it)
            }
        })
        return view
    }

    private fun setUpStatusObserver(formOculosViewModel: FormOculosViewModel){
        formOculosViewModel.status.observe(viewLifecycleOwner, Observer{status ->
            if (status)
                findNavController().popBackStack()
        })
    }
    private fun setUpMsgObserver(formOculosViewModel: FormOculosViewModel){
        formOculosViewModel.msg.observe(viewLifecycleOwner, Observer{msg ->
            if (!msg.isNullOrBlank()){
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun setUpImagemOculosObserver(viewModel: FormOculosViewModel){
        viewModel.imagemOculos.observe(viewLifecycleOwner, Observer{ imagemOculos ->
            if (imagemOculos != null){
                imageViewFormOculosFoto.setImageURI(imagemOculos)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        if(OculosEClienteUtil.oculosSelecionado != null)
            viewModelFormOculos.selectOculos(OculosEClienteUtil.oculosSelecionado!!.armacaoId!!)

        btnFormOculosProximo.setOnClickListener{
            LogRegister.getInstance(requireContext()).escreverLog("Cadastrar Óculos")

            val armacaoId = editTextOculosAmacaoId.text.toString()
            val lente = editTextOculosLente.text.toString()
            val marcaArmacao = editTextOculosMarca.text.toString()
            val cor = editTextOculosCor.text.toString()

            viewModelFormOculos.salvarOculos(armacaoId, lente, marcaArmacao, cor)

            //val viewModelFormOculos = ViewModelProvider(this).get(FormOculosDnpAlturaViewModel::class.java)
            findNavController().navigate(R.id.formOculosDnpAlturaFragment)
        }

        imageViewFormOculosFoto.setOnClickListener {
            selecionarImagem()
            //tirarFoto()
        }
    }

    private fun preencherFormulario(oculos: Oculos){
        editTextOculosAmacaoId.setText(oculos.armacaoId)
        editTextOculosLente.setText(oculos.lente)
        editTextOculosMarca.setText(oculos.marcaArmacao)
        editTextOculosCor.setText(oculos.cor)
        viewModelFormOculos.setUpImagemOculos(oculos.armacaoId!!)
    }

    private fun selecionarImagem(){
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, 2)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       super.onActivityResult(requestCode, resultCode, data)
       if (resultCode == Activity.RESULT_OK) {
           if (requestCode == 2) {
               val photo: Uri = data!!.data!!
               imageViewFormOculosFoto.setImageURI(photo)
               viewModelFormOculos.setImagemOculos(photo)
           }
       }
   }
}