package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.coment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import kotlinx.android.synthetic.main.comentario_oculos_fragment.*

class ComentarioOculosFragment : Fragment() {

    private lateinit var viewModelComentarioOculos: ComentarioOculosViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        viewModelComentarioOculos = ViewModelProvider(this).get(ComentarioOculosViewModel::class.java)

        viewModelComentarioOculos.comentarios.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()){
                listViewComentarios.adapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_list_item_1,
                    it
                )
            }
        })
        return inflater.inflate(R.layout.comentario_oculos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnComentarioOculosEnviar.setOnClickListener {
            val comentario = editTextComentarioOculos.text.toString()
            viewModelComentarioOculos.save(comentario)
        }

    }

}