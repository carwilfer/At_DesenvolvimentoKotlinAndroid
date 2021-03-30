package com.carwilfer.carlos_ferreira_dr3_tp1.ui.oculos.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.carwilfer.carlos_ferreira_dr3_tp1.LogRegister
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.adapter.RecyclerListOculos
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosEClienteUtil
import com.carwilfer.carlos_ferreira_dr3_tp1.database.OculosFirestoreDao
import com.carwilfer.carlos_ferreira_dr3_tp1.database.VendedorFirebaseDao
import kotlinx.android.synthetic.main.lista_oculos_fragment.*

class ListaOculosFragment : Fragment() {
    private lateinit var viewModelListaOculos: ListaOculosViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        verificarVendedor()

        val view = inflater.inflate(R.layout.lista_oculos_fragment, container, false)
        LogRegister.getInstance(requireContext()).escreverLog("Acessou: ListaOculosFragment")

        //val appDatabase = AppDatabase.getInstance(requireContext().applicationContext)
        //val oculosDao = appDatabase.oculosDao()

        //val oculosDao = AppDatabase.getInstance(requireContext().applicationContext).oculosDao() // abre a conexão com o app, pra pegar o oculosDao pra poder pessar para a ViewModel
        val listOculosViewModelFactory = ListOculosViewModelFactory(OculosFirestoreDao())

        viewModelListaOculos = ViewModelProvider(this, listOculosViewModelFactory).get(ListaOculosViewModel::class.java) //para poder passar parametro para a ViewModel, através da instancia da provider

        viewModelListaOculos.oculos.observe(viewLifecycleOwner, {
            recyclerViewListOculos.adapter = RecyclerListOculos(it){
                OculosEClienteUtil.oculosSelecionado = it
                findNavController().navigate(R.id.formOculosFragment)
            }
            recyclerViewListOculos.layoutManager = LinearLayoutManager(requireContext())
            /*listViewOculos.adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_list_item_1,
                it
            )
            listViewOculos.setOnItemClickListener { parent, view, position, id ->
                val oculos = it.get(position)
                OculosEClienteUtil.oculosSelecionado = oculos
                findNavController().navigate(R.id.formClienteFragment)
                //Toast.makeText(requireContext(),"${oculos.id}: ${oculos.marca}: ${oculos.grau}", Toast.LENGTH_LONG).show()*/
        })
            viewModelListaOculos.atualizarListaOculos()

        //viewModelListaOculos.atualizarListaOculos()
        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        fabFormOculos.setOnClickListener{
            OculosEClienteUtil.oculosSelecionado = null
            findNavController().navigate(R.id.formOculosFragment)
        }
        fabConfig.setOnClickListener{
            findNavController().navigate(R.id.configOculosFragment)
        }

    }

    fun verificarVendedor(){
        if (VendedorFirebaseDao.firebaseAuth.currentUser == null)
            findNavController().popBackStack()
    }

}