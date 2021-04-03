package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.form

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.LogRegister
import com.carwilfer.carlos_ferreira_dr3_tp1.R
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
    ///private lateinit var fabFormOculosSalvar: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?

    {
        val view = inflater.inflate(R.layout.form_oculos_fragment, container, false)

        LogRegister.getInstance(requireContext()).escreverLog("Acessou: FormOculosFragment")

        /*val appDatabase = AppDatabase.getInstance(requireContext().applicationContext)
        val oculosDao = appDatabase.oculosDao()
        val formOculosViewModelFactory = FormOculosViewModelFactory(oculosDao)
        viewModel = ViewModelProvider(this, formOculosViewModelFactory).get(FormOculosViewModel::class.java)*/

        val application = requireActivity().application
        val formOculosViewModelFactory = FormOculosViewModelFactory(OculosFirestoreDao(), application)

        viewModelFormOculos = ViewModelProvider(this, formOculosViewModelFactory).get(FormOculosViewModel::class.java).apply {
            setUpMsgObserver(this)
            setUpStatusObserver(this)
            setUpImagemOculosObserver(this)
        }
        formViewModelCliente = ViewModelProvider(this).get(ListaClienteViewModel::class.java)
        formViewModelCliente.clientes.observe(viewLifecycleOwner){
            var adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                it
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinnerListaCliente.adapter = adapter
        }
        //return view

        viewModelFormOculos.oculos.observe(viewLifecycleOwner, {
            if (it != null){
                preencherFormulario(it)
            }
        })
        return view
    }

    private fun setUpStatusObserver(formOculosViewModel: FormOculosViewModel){
        formOculosViewModel.status.observe(viewLifecycleOwner, {status ->
            if (status)
                findNavController().popBackStack()
        })
    }
    private fun setUpMsgObserver(formOculosViewModel: FormOculosViewModel){
        formOculosViewModel.msg.observe(viewLifecycleOwner, {msg ->
            if (!msg.isNullOrBlank()){
                Toast.makeText(requireContext(), msg, Toast.LENGTH_LONG).show()
            }
        })
    }
    private fun setUpImagemOculosObserver(viewModel: FormOculosViewModel){
        viewModel.imagemOculos.observe(viewLifecycleOwner, {imagemOculos ->
            if (imagemOculos != null){
                imageViewFormOculosFoto.setImageURI(imagemOculos)
            }
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)
        /*if (OculosEClienteUtil.oculosSelecionado != null){
            fabFormOculosComentarios.visibility = View.VISIBLE
            viewModelFormOculos.selectOculos(OculosEClienteUtil.oculosSelecionado!!.armacaoId!!)
        }else{
            fabFormOculosComentarios.visibility = View.GONE
        }*/

        btnFormOculosProximo.setOnClickListener{
            LogRegister.getInstance(requireContext()).escreverLog("Cadastrar Óculos")

            val armacaoId = editTextOculosAmacaoId.text.toString()
            /*val dnpOlhoDireito = editTextDnpOd.text.toString()
            val dnpOlhoEsquedo = editTextDnpOe.text.toString()
            val alturaOlhoDireito = editTextAlturaOd.text.toString()
            val alturaOlhoEsquerdo = editTextAlturaOe.text.toString()
            val esfericoLongeOlhoDireito = editTextEsfericoLongeOd.text.toString()
            val esfericoLongeOlhoEsquedo = editTextEsfericoLongeEs.text.toString()
            val esfericoPertoOlhoDireito = editTextEsfericoPertoOd.text.toString()
            val esfericoPertoOlhoEsquedo = editTextEsfericoPertoEs.text.toString()
            val cilindricoLongeOlhoDireito = editTextCilindricoLongeOd.text.toString()
            val cilindricoLongeOlhoEsquedo = editTextCilindricoLongeOe.text.toString()
            val cilindricoPertoOlhoDireito = editTextCilindricoPertoOd.text.toString()
            val cilindricoPertoOlhoEsquedo = editTextCilindricoPertoEs.text.toString()
            val eixoLongeOlhoDireito = editTextEixoLongeOd.text.toString()
            val eixoLongeOlhoEsquedo = editTextEixoLongeOe.text.toString()
            val eixoPertoOlhoDireito = editTextEixoPertoOd.text.toString()
            val eixoPertoOlhoEsquedo = editTextEixoPertoEs.text.toString()*/
            val lente = editTextOculosLente.text.toString()
            val marcaArmacao = editTextOculosMarca.text.toString()
            val cor = editTextOculosCor.text.toString()

            viewModelFormOculos.salvarOculos(armacaoId, /*dnpOlhoDireito, dnpOlhoEsquedo, alturaOlhoDireito, alturaOlhoEsquerdo, esfericoLongeOlhoDireito, esfericoLongeOlhoEsquedo, esfericoPertoOlhoDireito,
                    esfericoPertoOlhoEsquedo, cilindricoLongeOlhoDireito, cilindricoLongeOlhoEsquedo, cilindricoPertoOlhoDireito, cilindricoPertoOlhoEsquedo, eixoLongeOlhoDireito, eixoLongeOlhoEsquedo,
                    eixoPertoOlhoDireito, eixoPertoOlhoEsquedo,*/ lente, marcaArmacao, cor
            )
            findNavController().navigate(R.id.formOculosFragment2)
        }

        imageViewFormOculosFoto.setOnClickListener {
            selecionarImagem()
            //tirarFoto()
        }

        /*fabFormOculosComentarios.setOnClickListener {
            findNavController().navigate(R.id.formOculosFragment2)
        }*/
    }

    private fun preencherFormulario(oculos: Oculos){
        editTextOculosAmacaoId.setText(oculos.armacaoId)
        /*editTextDnpOd.setText(oculos.dnpOlhoDireito)
        editTextDnpOe.setText(oculos.dnpOlhoEsquedo)
        editTextAlturaOd.setText(oculos.alturaOlhoDireito)
        editTextAlturaOe.setText(oculos.alturaOlhoEsquedo)
        editTextEsfericoLongeOd.setText(oculos.esfericoLongeOlhoDireito)
        editTextEsfericoLongeEs.setText(oculos.esfericoLongeOlhoDireito)
        editTextEsfericoPertoOd.setText(oculos.esfericoPertoOlhoDireito)
        editTextEsfericoPertoEs.setText(oculos.esfericoLongeOlhoEsquedo)
        editTextCilindricoLongeOd.setText(oculos.cilindricoLongeOlhoDireito)
        editTextCilindricoLongeOe.setText(oculos.cilindricoLongeOlhoEsquedo)
        editTextCilindricoPertoOd.setText(oculos.cilindricoPertoOlhoDireito)
        editTextCilindricoPertoEs.setText(oculos.cilindricoPertoOlhoEsquedo)
        editTextEixoLongeOd.setText(oculos.eixoLongeOlhoDireito)
        editTextEixoLongeOe.setText(oculos.eixoLongeOlhoEsquedo)
        editTextEixoPertoOd.setText(oculos.eixoPertoOlhoDireito)
        editTextEixoPertoEs.setText(oculos.eixoPertoOlhoEsquedo)*/
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

    /*private fun tirarFoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent.resolveActivity(requireActivity().packageManager) != null)
            startActivityForResult(intent, 1)
        else
            Toast.makeText(
                    requireContext(),
                    "Nenhum recurso disponível.",
                    Toast.LENGTH_LONG).show()
    }*/

   /* private fun limparFormulário(){
        editTextOculosMarca.setText("")
        editTextOculosCor.setText("")
        editTextOculosLente.setText("")
        editTextOculosGrau.setText("")
    }*/
   override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
       super.onActivityResult(requestCode, resultCode, data)
       if (resultCode == Activity.RESULT_OK) {
           if (requestCode == 2) {
               val photo: Uri = data!!.data!!
               imageViewFormOculosFoto.setImageURI(photo)
               viewModelFormOculos.setImagemOculos(photo)
           }
       }
       /*if(resultCode == Activity.RESULT_OK) {
           var imageBitmap = data?.extras?.get("data") as Bitmap
           imageViewFormOculosFoto.setImageBitmap(imageBitmap)
           viewModelFormOculos.imagemOculos = imageBitmap
       }*/
   }
}