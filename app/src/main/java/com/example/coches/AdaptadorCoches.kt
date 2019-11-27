package com.example.coches

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class AdaptadorCoches (var list: ArrayList<Coche>): RecyclerView.Adapter<AdaptadorCoches.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.content_item,parent,false)


        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: AdaptadorCoches.ViewHolder, position: Int) {
        holder.bindItems(list[position])

    }


    class ViewHolder(view: View): RecyclerView.ViewHolder(view){

        fun bindItems(data:Coche){
            val matricula:TextView=itemView.findViewById(R.id.matricula)
            val marca:TextView=itemView.findViewById(R.id.marca)
            val modelo:TextView=itemView.findViewById(R.id.modelo)
            val combustible:TextView=itemView.findViewById(R.id.combustible)
            val precio:TextView=itemView.findViewById(R.id.precio)
            val imgcoche:ImageView=itemView.findViewById(R.id.imgCoche)

            matricula.text=data.matricula
            marca.text=data.marca
            modelo.text=data.modelo
            combustible.text=data.combustible
            precio.text=data.precio.toString()
            Glide.with(itemView.context).load(data.imgcoche).into(imgcoche)



            itemView.setOnClickListener{
                Toast.makeText( itemView.context,"${data.matricula}", Toast.LENGTH_LONG).show()
            }



        }

    }
}