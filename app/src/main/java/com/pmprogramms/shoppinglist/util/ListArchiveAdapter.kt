package com.pmprogramms.shoppinglist.util

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel

class ListArchiveAdapter : RecyclerView.Adapter<ListArchiveAdapter.ListHolder>() {
    private var shopListItems: List<ShopList> = emptyList()
    private lateinit var viewModel: ShopListViewModel

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.text_title)
        var rowItem: ConstraintLayout = itemView.findViewById(R.id.row_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
       viewModel = ViewModelProviders.of(parent.context as FragmentActivity).get(ShopListViewModel::class.java)
        return ListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shoplist_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val currentItem = shopListItems[position]
        holder.name.text = currentItem.title
        holder.rowItem.setOnLongClickListener(View.OnLongClickListener { v ->
            viewModel.unArchiveAction(currentItem.id)
            return@OnLongClickListener true
        })
        holder.rowItem.setOnClickListener {
            Toast.makeText(holder.rowItem.context, "Please unarchive data for displaying details!", Toast.LENGTH_LONG).show()
        }
    }

    override fun getItemCount(): Int {
        return shopListItems.size
    }

    fun setData(shopListItems: List<ShopList>) {
        this.shopListItems = shopListItems
        notifyDataSetChanged()
    }
}