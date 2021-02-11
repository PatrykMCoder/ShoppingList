package com.pmprogramms.shoppinglist.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.fragments.MainFragmentDirections

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    private var shopList = emptyList<ShopList>()

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView = itemView.findViewById(R.id.text_title)
        var rowItem: ConstraintLayout = itemView.findViewById(R.id.row_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        return ListHolder(LayoutInflater.from(parent.context).inflate(R.layout.shoplist_item, parent,false))
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val currentItem = shopList[position]
        holder.title.text = currentItem.title
        holder.rowItem.setOnClickListener {
            val action = MainFragmentDirections.actionMainFragmentToShopListDetailsFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    fun setData(shopList: List<ShopList>) {
        this.shopList = shopList
        notifyDataSetChanged()
    }
}

