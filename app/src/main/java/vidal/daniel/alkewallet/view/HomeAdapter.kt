package vidal.daniel.alkewallet.view

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.databinding.HomeItemLayoutBinding
import vidal.daniel.alkewallet.model.HomeModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HomeAdapter(private val txList: List<HomeModel>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item_layout, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val tx = txList[position]
        holder.bind(tx)
    }

    override fun getItemCount(): Int {
        return txList.size
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Obtengo el logo del usuario logueado
        // val someValue =  sharedPreferences.getString("some_key", "default_value")


        private val imagenView : ImageView = itemView.findViewById(R.id.imageView)
        private val conceptoTextView: TextView = itemView.findViewById(R.id.conceptoTextView)
        // private val usuarioTextView: TextView = itemView.findViewById(R.id.usuarioTextView)
        private val fechaTextView: TextView = itemView.findViewById(R.id.fechaTextView)
        private val montoTextView: TextView = itemView.findViewById(R.id.montoTextView)

        fun bind(tx: HomeModel) {
            imagenView.setImageResource(R.drawable.dv)
            conceptoTextView.text   = tx.concept.toString()
            // usuarioTextView.text    = tx.userId.toString()
            fechaTextView.text      = formatDateToString(tx.date)
            montoTextView.text      = tx.amount.toString()
        }

        fun formatDateToString(date: Date): String {
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            return dateFormat.format(date)
        }


    }
}

