package com.ankushsoni.randomquotes.view.viewHolder

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ankushsoni.randomquotes.R
import com.ankushsoni.randomquotes.model.QuotesTable


class TestingFavViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var quotesTextView : TextView = itemView.findViewById(R.id.quotes_text)
    var authorTextView : TextView = itemView.findViewById(R.id.quotes_author)
    var copyBtn : Button = itemView.findViewById<Button>(R.id.copy_btn)
    var deleteBtn : Button = itemView.findViewById<Button>(R.id.delete_btn)

    fun bind(item : QuotesTable){
        quotesTextView.text = item.quote
        authorTextView.text = item.author
    }

}