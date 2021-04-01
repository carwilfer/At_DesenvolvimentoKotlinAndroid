package com.carwilfer.carlos_ferreira_dr3_tp1.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.model.Multimedia
import com.carwilfer.carlos_ferreira_dr3_tp1.model.News

class NewsRecyclerAdapter (
    private val news: List<News>
) : RecyclerView.Adapter<NewsRecyclerAdapter.NewsViewHolder>(){

    class NewsViewHolder(itemView : View): RecyclerView.ViewHolder(itemView){
    val textViewListNewsAssunto: TextView = itemView.findViewById(R.id.textViewListNewsAssunto)
    val textViewListNewsConteudo: TextView = itemView.findViewById(R.id.textViewListNewsConteudo)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycle_news_layout, parent, false)
        return NewsViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news = news[position]
        holder.textViewListNewsAssunto.text = news.web_url
        holder.textViewListNewsConteudo.text = news.print_page.toString()
    }

    override fun getItemCount(): Int = news.size
}