package com.carwilfer.carlos_ferreira_dr3_tp1.ui.cliente.form

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import kotlinx.android.synthetic.main.form_cliente_fragment.*

class FormClienteFragment : Fragment() {

    private lateinit var viewModelFormCliente: FormClienteViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModelFormCliente = ViewModelProvider(this).get(FormClienteViewModel::class.java)
        viewModelFormCliente.let {
            it.status.observe(viewLifecycleOwner, { status ->
                if (status)
                    findNavController().popBackStack()
            })
            it.msg.observe(viewLifecycleOwner, {msg ->
                if (!msg.isNullOrBlank())
                    Toast.makeText(
                            requireContext(), msg, Toast.LENGTH_LONG
                    ).show()
            })
            it.oculos.observe(viewLifecycleOwner, { list->
                val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_spinner_item,
                        list
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerFormClienteOculos.adapter = adapter

                spinnerFormClienteOculos.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
                    override fun onNothingSelected(parent: AdapterView<*>?) {

                    }

                    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                        it.definirArmacaoOculosSelecionada(list[position])
                    }
                }
            })
        }


        return inflater.inflate(R.layout.form_cliente_fragment, container, false)
    }

    /*override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModelFormCliente = ViewModelProvider(this).get(FormClienteViewModel::class.java)
        // TODO: Use the ViewModel
    }*/

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabFormClienteSalvar.setOnClickListener{
            val nome = editTextFormeClienteNome.text.toString()
            val endereco = editTextFormeClienteEndereco.text.toString()
            val telefone = editTextFormeClienteTelefone.text.toString()
            val cpf = editTextFormeClienteCPF.text.toString()
            viewModelFormCliente.salvarCliente(nome, endereco, telefone, cpf)

        }
        imageViewCliente.setOnClickListener{
            tirarFoto()
        }
    }

    private fun tirarFoto() {
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 1)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            var imageBitmap = data?.extras?.get("data") as Bitmap
            imageViewCliente.setImageBitmap(imageBitmap)
            viewModelFormCliente.alterarImagemPerfil(imageBitmap)
        }

    }
    private fun showToast(msg: String) {
        Toast
            .makeText(
                requireContext(),
                msg,
                Toast.LENGTH_LONG
            ).show()
    }
}