package com.carwilfer.carlos_ferreira_dr3_tp1.ui.home.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import kotlinx.android.synthetic.main.home_menu_fragment.*

class HomeMenuFragment : Fragment() {

    private lateinit var viewModel: HomeMenuViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_menu_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(HomeMenuViewModel::class.java)

        buttonClientes.setOnClickListener{
            findNavController().navigate(R.id.listaClienteFragment)
        }
        buttonOculos.setOnClickListener{
            findNavController().navigate(R.id.listaOculosFragment)
        }
        // TODO: Use the ViewModel
    }

}