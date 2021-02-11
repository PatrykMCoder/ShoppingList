package com.pmprogramms.shoppinglist.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.data.json.Item
import com.pmprogramms.shoppinglist.fragments.MainFragmentDirections
import com.pmprogramms.shoppinglist.util.json.JSONUtil
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel

class ListAdapter : RecyclerView.Adapter<ListAdapter.ListHolder>() {

    private var shopList = emptyList<ShopList>()
    private lateinit var viewModel: ShopListViewModel

    class ListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.text_title)
        var countCollected: TextView = itemView.findViewById(R.id.text_checked_elements)
        var rowItem: ConstraintLayout = itemView.findViewById(R.id.row_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListHolder {
        viewModel = ViewModelProviders.of(parent.context as FragmentActivity)
            .get(ShopListViewModel::class.java)
        return ListHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.shoplist_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListHolder, position: Int) {
        val currentItem = shopList[position]
        holder.name.text = currentItem.title
        holder.countCollected.text = "Groceries done ${calculateGroceries(currentItem.items)}"
        holder.rowItem.setOnClickListener {
            val action =
                MainFragmentDirections.actionMainFragmentToShopListDetailsFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }

        holder.rowItem.setOnLongClickListener(View.OnLongClickListener { v ->
            viewModel.archiveAction(currentItem.id)
            return@OnLongClickListener true
        })
    }

    override fun getItemCount(): Int {
        return shopList.size
    }

    fun setData(shopList: List<ShopList>) {
        this.shopList = shopList
        notifyDataSetChanged()
    }

    private fun calculateGroceries(item: String): String {
        val items = JSONUtil().readFromJSON(item)
        val allSize = items.size
        val doneList = emptyList<Item>().toMutableList()
        for (i in items) {
            if (i.item_collect)
                doneList.add(i)
        }
        val doneSize = doneList.size

        return "$doneSize/$allSize"
    }
}

