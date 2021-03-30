package com.carwilfer.carlos_ferreira_dr3_tp1.adapter

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.carwilfer.carlos_ferreira_dr3_tp1.R
import com.carwilfer.carlos_ferreira_dr3_tp1.model.OculosLite
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.recycler_list_oculos.view.*

class RecyclerListOculos (
    private val oculos: List<OculosLite>,
    val actionClick: (OculosLite) -> Unit
        ): RecyclerView.Adapter<RecyclerListOculos.OculosViewHolder>(){


    private val storageReference = FirebaseStorage.getInstance().reference

    class OculosViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textMarcaArmacao: TextView = itemView.textViewOculosMarcaArmação
        val textLente: TextView = itemView.textViewOculosLente
        val textArmacaoId: TextView = itemView.textViewOculosArmacaoId
        val imagemOculos: ImageView = itemView.imageViewOculosFoto

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OculosViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(
                R.layout.recycler_list_oculos,
                parent,
                false
            )
        return OculosViewHolder(view)
    }

    override fun onBindViewHolder(holder: OculosViewHolder, position: Int) {
        val oculos = oculos[position]
        holder.textMarcaArmacao.text = oculos.marcaArmacao
        holder.textLente.text = oculos.lente
        holder.textArmacaoId.text = oculos.armacaoId

        val fileReference = storageReference.child("Oculos/imagens/${oculos.armacaoId}.png")
        fileReference
            .getBytes(1024*1024)
            .addOnSuccessListener {         // it: ByteArray
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                holder.imagemOculos.setImageBitmap(bitmap)
            }
            .addOnFailureListener {
                Log.i("RecyclerImageOculos", "${it.message}")
            }

        holder.itemView.setOnClickListener {
            actionClick(oculos)
        }

    }
    override fun getItemCount(): Int = oculos.size
}