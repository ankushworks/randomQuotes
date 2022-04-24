package com.ankushsoni.randomquotes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.ankushsoni.randomquotes.R
import com.ankushsoni.randomquotes.model.QuotesTable
import com.ankushsoni.randomquotes.view.viewHolder.FavViewHolder

class FavAdapter(val listener : RowClicksListener)  : RecyclerView.Adapter<FavViewHolder>() {

    lateinit var  items : ArrayList<QuotesTable>

    fun setData(data  : ArrayList<QuotesTable>){
        this.items = data
        notifyDataSetChanged();
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_items_layout , parent ,  false)
        return FavViewHolder(view , listener)

    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        val currentItem = items[position]
        holder.quotesTextView.text = currentItem.quote
        holder.authorTextView.text = currentItem.author
        holder.copyBtn.setOnClickListener {
            Log.d("FAVADAPTER" , "ShareBtn Clicked")
            listener.onCopyItemClickListener(items[position])
        }
        holder.deleteBtn.setOnClickListener {
            Log.d("FAVADAPTER" , "DeleteBtn Clicked")
            listener.onDeleteItemClickListener(items[position])
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }


    interface RowClicksListener{

        fun onDeleteItemClickListener(quotes : QuotesTable)
        fun onCopyItemClickListener(quotes : QuotesTable)
    }
}


