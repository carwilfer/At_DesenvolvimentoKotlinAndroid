package com.carwilfer.carlos_ferreira_dr3_tp1.ui.vendedor.perfil

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Vendedor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.perfil_vendedor_fragment.*

class PerfilVendedorFragment : Fragment() {

    private lateinit var viewModelPerfilVendedor: PerfilVendedorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val perfilVendedorViewModelFactory = PerfilVendedorViewModelFactory(requireActivity().application)
        viewModelPerfilVendedor = ViewModelProvider(this, perfilVendedorViewModelFactory).get(PerfilVendedorViewModel::class.java)

        viewModelPerfilVendedor.vendedor.observe(viewLifecycleOwner, { vendedor ->
            if (vendedor != null)
                preencherInformaçãoDoPerfil(vendedor)
            else{
                requireActivity().bottomNavigationView.visibility = View.GONE
                findNavController().navigate(R.id.loginVendedorFragment)
                limparInformacaoDoPerfil()
            }
        })
        viewModelPerfilVendedor.imagemPerfilVendedor.observe(viewLifecycleOwner, {
            if(it != null)
                imageViewPerfilFotoVendedor.setImageURI(it)
        })
        return inflater.inflate(R.layout.perfil_vendedor_fragment, container, false)
    }

    private fun limparInformacaoDoPerfil() {
        textViewPerfilUidVendedor.text = null
        textViewPerfilNomeVendedor.text = null
        textViewPerfilTelefoneVendedor.text = null
        textViewPerfilOticaVendedor.text = null
        textViewPerfilEmailVendedor.text = null
    }

    private fun preencherInformaçãoDoPerfil(vendedor: Vendedor) {
        textViewPerfilUidVendedor.text = vendedor.uid
        textViewPerfilNomeVendedor.text = vendedor.nome
        textViewPerfilTelefoneVendedor.text = vendedor.telefone
        textViewPerfilOticaVendedor.text = vendedor.otica
        textViewPerfilEmailVendedor.text = vendedor.firebaseUser!!.email
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        btnPerfilVendedorSair.setOnClickListener{
            viewModelPerfilVendedor.encerrarSessao()
        }
    }
}