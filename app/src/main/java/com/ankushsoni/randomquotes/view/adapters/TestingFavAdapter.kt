package com.ankushsoni.randomquotes.view.adapters


import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.ankushsoni.randomquotes.R
import com.ankushsoni.randomquotes.model.QuotesTable
import com.ankushsoni.randomquotes.view.viewHolder.TestingFavViewHolder


class TestingFavAdapter(val listener : TestingFavAdapter.RowClicksListener) : ListAdapter<QuotesTable, TestingFavViewHolder>(DiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TestingFavViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fav_items_layout , parent , false)
        return  TestingFavViewHolder(view)
    }

    override fun onBindViewHolder(holder: TestingFavViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
        holder.copyBtn.setOnClickListener {
            Log.d("FAVADAPTER" , item.toString())
            listener.onCopyItemClickListener(item)
        }
        holder.deleteBtn.setOnClickListener {
            Log.d("FAVADAPTER" , item.toString())
            listener.onDeleteItemClickListener(item)
        }
    }

    class  DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<QuotesTable>(){

        override fun areItemsTheSame(oldItem: QuotesTable, newItem: QuotesTable): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: QuotesTable, newItem: QuotesTable): Boolean {
            return oldItem == newItem
        }

    }


    interface RowClicksListener{

        fun onDeleteItemClickListener(quotes : QuotesTable)
        fun onCopyItemClickListener(quotes : QuotesTable)
    }

}