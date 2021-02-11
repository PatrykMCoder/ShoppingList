package com.pmprogramms.shoppinglist.adapters

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.data.json.Item
import com.pmprogramms.shoppinglist.util.json.JSONUtil
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel

class ListDetailsAdapter : RecyclerView.Adapter<ListDetailsAdapter.ListDetailsHolder>() {
    private var id: Int = -1
    private var shopListItems: List<Item> = emptyList()
    private lateinit var viewModel: ShopListViewModel

    class ListDetailsHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.text_item_name)
        val count: TextView = itemView.findViewById(R.id.text_item_count)
        val checked: CheckBox = itemView.findViewById(R.id.item_checked)
        var container: ConstraintLayout = itemView.findViewById(R.id.row_item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListDetailsHolder {
        viewModel = ViewModelProviders.of(parent.context as FragmentActivity)
            .get(ShopListViewModel::class.java)

        return ListDetailsHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.shoplist_details_item, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ListDetailsHolder, position: Int) {
        val current = shopListItems[position]

        updateUI(current.item_collect, holder)

        holder.name.text = current.item_name
        holder.count.text = "x${current.item_count}"
        holder.checked.isChecked = current.item_collect

        holder.checked.setOnCheckedChangeListener { buttonView, isChecked ->
            run {
                updateUI(isChecked, holder)
                current.item_collect = isChecked
                val jsonString = JSONUtil()
                    .generateJSONString(shopListItems)
                viewModel.updateShopList(id, jsonString)
            }
        }

        holder.container.setOnLongClickListener(View.OnLongClickListener {
            val items = shopListItems.toMutableList()
            items.removeAt(position)
            this.shopListItems = items
            val jsonString = JSONUtil().generateJSONString(shopListItems)
            viewModel.updateShopList(id, jsonString)
            notifyDataSetChanged()
            return@OnLongClickListener true
        })
    }

    override fun getItemCount(): Int {
        return shopListItems.size
    }

    fun setData(shopListItems: List<Item>, id: Int) {
        this.shopListItems = shopListItems
        this.id = id
        notifyDataSetChanged()
    }

    private fun updateUI(collected: Boolean, holder: ListDetailsHolder) {
        if (collected) {
            holder.name.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            holder.count.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
        } else {
            holder.name.paintFlags = 0
            holder.count.paintFlags = 0
        }
    }
}