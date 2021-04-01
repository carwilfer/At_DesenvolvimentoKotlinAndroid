package com.carwilfer.carlos_ferreira_dr3_tp1.ui.api.form

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class FormNewsFragment : Fragment() {

    private lateinit var viewModelFormNews: FormNewsViewModel

    private lateinit var editTextFormNewsAssunto: EditText
    private lateinit var editTextFormNewsConteudo: EditText
    private lateinit var fabFormNewsSalvar: FloatingActionButton


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.form_news_fragment, container, false)

        editTextFormNewsAssunto = view.findViewById(R.id.editTextFormNewsAssunto)
        editTextFormNewsConteudo = view.findViewById(R.id.editTextFormNewsConteudo)
        fabFormNewsSalvar = view.findViewById(R.id.fabFormNewsSalvar)

        viewModelFormNews = ViewModelProvider(this).get(FormNewsViewModel::class.java)

        viewModelFormNews.status.observe(viewLifecycleOwner, Observer {
            if (it)
                findNavController().popBackStack()
        })

        viewModelFormNews.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
        })
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabFormNewsSalvar.setOnClickListener {
            val assunto = editTextFormNewsAssunto.text.toString()
            val conteudo = editTextFormNewsConteudo.text.toString()

            if (assunto.isNullOrBlank() && !conteudo.isNullOrBlank()){
                viewModelFormNews.save(assunto, conteudo)
            }
        }

    }

}