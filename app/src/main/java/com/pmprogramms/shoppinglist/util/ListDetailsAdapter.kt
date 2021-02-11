package com.pmprogramms.shoppinglist.util

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.data.json.Item

class ListDetailsAdapter : RecyclerView.Adapter<ListDetailsAdapter.ListDetailsHolder>() {
    private var shopListItems: List<Item> = emptyList()

    class ListDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.text_item_name)
        val count = itemView.findViewById<TextView>(R.id.text_item_count)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailsHolder {
        return ListDetailsHolder(LayoutInflater.from(parent.context).inflate(R.layout.shoplist_details_item, parent, false))
    }

    override fun onBindViewHolder(holder: ListDetailsHolder, position: Int) {
        val current = shopListItems[position]
        holder.name.text = current.item_name
        holder.count.text = "x${current.item_count}"
    }

    override fun getItemCount(): Int {
        return shopListItems.size
    }

    fun setData(shopListItems: List<Item>) {
        this.shopListItems = shopListItems
        notifyDataSetChanged()
    }
}