package com.carwilfer.carlos_ferreira_dr3_tp1.ui.vendedor.cadastro

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import kotlinx.android.synthetic.main.cadastro_vendedor_fragment.*

class CadastroVendedorFragment : Fragment() {

    private lateinit var viewModelCadastroVendedor: CadastroVendedorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModelCadastroVendedor = ViewModelProvider(this).get(CadastroVendedorViewModel::class.java)
        viewModelCadastroVendedor.status.observe(viewLifecycleOwner, {
            if (it)
                findNavController().popBackStack()
        })
        viewModelCadastroVendedor.msg.observe(viewLifecycleOwner, {
            if (!it.isNullOrBlank())
                showToast(it)
        })
        return inflater.inflate(R.layout.cadastro_vendedor_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnFormCadastroSalvarVendedor.setOnClickListener {

            val senha = editTextFormCadastroSenhaVendedor.text.toString()
            val resenha = editTextFormCadastroReSenhaVendedor.text.toString()

            if (senha == resenha) {
                val nome = editTextFormCadastroNomeVendedor.text.toString()
                val telefone = editTextFormCadastroTelefoneVendedor.text.toString()
                val email = editTextFormCadastroEmailVendedor.text.toString()
                viewModelCadastroVendedor.salvarCadastroVendedor(email, senha, nome, telefone)
            } else
                showToast("Senhas não conferem")
        }

        imageViewFormCadastroFotoVendedor.setOnClickListener {
            tirarFotoVendedor()
        }
    }

    private fun tirarFotoVendedor(){
        val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, 1)
    }

    private fun showToast(msg: String) {
        Toast
                .makeText(
                        requireContext(),
                        msg,
                        Toast.LENGTH_LONG
                ).show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(resultCode == Activity.RESULT_OK) {
            var imageBitmap = data?.extras?.get("data") as Bitmap
            imageViewFormCadastroFotoVendedor.setImageBitmap(imageBitmap)
            viewModelCadastroVendedor.alterarImagemPerfilVendedor(imageBitmap)
        }

    }

}