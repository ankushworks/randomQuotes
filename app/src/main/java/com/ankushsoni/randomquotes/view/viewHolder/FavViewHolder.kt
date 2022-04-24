package com.ankushsoni.randomquotes.view.viewHolder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankushsoni.randomquotes.R
import com.ankushsoni.randomquotes.view.adapters.FavAdapter


class FavViewHolder(itemView: View, listener: FavAdapter.RowClicksListener) : RecyclerView.ViewHolder(itemView) {
    var quotesTextView : TextView = itemView.findViewById(R.id.quotes_text)
    var authorTextView : TextView = itemView.findViewById(R.id.quotes_author)
    var copyBtn : Button = itemView.findViewById<Button>(R.id.copy_btn)
    var deleteBtn : Button = itemView.findViewById<Button>(R.id.delete_btn)

}