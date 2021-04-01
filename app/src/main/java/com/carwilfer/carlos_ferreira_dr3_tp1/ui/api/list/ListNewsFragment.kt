package com.carwilfer.carlos_ferreira_dr3_tp1.ui.api.list

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.adapter.NewsRecyclerAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar

class ListNewsFragment : Fragment() {

    private lateinit var viewModelListNews: ListNewsViewModel
    private lateinit var recycleViewListNews: RecyclerView
    private lateinit var fabListNewsToForm: FloatingActionButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.list_news_fragment, container, false)

        recycleViewListNews = view.findViewById(R.id.recycleViewListNews)
        fabListNewsToForm = view.findViewById(R.id.fabListNewsToForm)

        viewModelListNews = ViewModelProvider(this).get(ListNewsViewModel::class.java)
        viewModelListNews.news.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty())
                recycleViewListNews.adapter = NewsRecyclerAdapter(it)
        })
        viewModelListNews.msg.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrBlank())
                Snackbar.make(view, it, Snackbar.LENGTH_LONG).show()
        })

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fabListNewsToForm.setOnClickListener {
            findNavController().navigate(R.id.action_listNewsFragment_to_formNewsFragment)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        // TODO: Use the ViewModel
    }

}