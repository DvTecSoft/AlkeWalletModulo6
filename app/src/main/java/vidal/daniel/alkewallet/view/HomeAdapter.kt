package vidal.daniel.alkewallet.view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import vidal.daniel.alkewallet.R
import vidal.daniel.alkewallet.model.TransaccionModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HomeAdapter(private val txList: List<TransaccionModel>?) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.home_item_layout, parent, false)
        return HomeViewHolder(view)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val tx = txList?.get(position)
        holder.bind(tx)
    }

    override fun getItemCount(): Int
    {
        return txList?.size ?: 0
    }

    class HomeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Obtengo el logo del usuario logueado
        // val someValue =  sharedPreferences.getString("some_key", "default_value")
        //private val imagenView : ImageView = itemView.findViewById(R.id.imageView)
        private val conceptoTextView: TextView = itemView.findViewById(R.id.conceptoTextView)
        // private val usuarioTextView: TextView = itemView.findViewById(R.id.usuarioTextView)
        private val fechaTextView: TextView = itemView.findViewById(R.id.fechaTextView)
        private val montoTextView: TextView = itemView.findViewById(R.id.montoTextView)

        fun bind(tx: TransaccionModel?) {
            // imagenView.setImageResource(R.drawable.dv) -> Agrega imagen desde el drawabe
            //Picasso.get().load(tx.url_imagen).placeholder(R.drawable.dv).error(R.drawable.dv).into(imagenView)

            if (tx != null)
            {
                conceptoTextView.text   = tx.concept
                //usuarioTextView.text    = tx.userId.toString()
                fechaTextView.text      = formatDateToString(tx.date)
                montoTextView.text      = tx.amount.toString()
            }
        }

        fun formatDateToString(date: Date?): String {
            val dateFormat = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
            return dateFormat.format(date)
        }


    }
}

