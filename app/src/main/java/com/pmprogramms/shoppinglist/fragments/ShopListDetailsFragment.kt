package com.pmprogramms.shoppinglist.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.data.json.Item
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel
import com.pmprogramms.shoppinglist.databinding.FragmentShopListDetailsBinding
import com.pmprogramms.shoppinglist.util.json.JSONUtil
import com.pmprogramms.shoppinglist.adapters.ListDetailsAdapter
import com.pmprogramms.shoppinglist.util.text.TextHelper

class ShopListDetailsFragment : Fragment() {
    private lateinit var binding: FragmentShopListDetailsBinding
    private val args by navArgs<ShopListDetailsFragmentArgs>()
    private lateinit var viewModel: ShopListViewModel
    private lateinit var adapter: ListDetailsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopListDetailsBinding.inflate(layoutInflater)
        binding.add.setOnClickListener {
            addShopListElement()
        }

        adapter = ListDetailsAdapter()
        val items = JSONUtil().readFromJSON(args.shoplist.items)
        binding.recyclerDetails.adapter = adapter
        binding.recyclerDetails.layoutManager = LinearLayoutManager(requireContext())
        adapter.setData(items, args.shoplist.id)

        viewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.delete_action -> {
                deleteShopList()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun deleteShopList() {
        val builder = AlertDialog.Builder(requireContext())

        builder.setPositiveButton("Yes") { _: DialogInterface, _: Int ->
            viewModel.deleteSingleList(args.shoplist)
            findNavController().navigate(R.id.action_shopListDetailsFragment_to_mainFragment)
        }

        builder.setNegativeButton("No") { dialogInterface: DialogInterface, _ ->
            dialogInterface.dismiss()
        }

        builder.setTitle("Delete")
        builder.setMessage("Do you want delete ${args.shoplist.title}")
        builder.create().show()
    }

    private fun addShopListElement() {
        val builder = AlertDialog.Builder(requireContext())
        val view =
            LayoutInflater.from(requireContext()).inflate(R.layout.add_item_layout, null, false)
        builder.setView(view)
        builder.setTitle("Add element")

        val itemName = view.findViewById<EditText>(R.id.text_item_name)
        itemName.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f
        )

        val itemCount = view.findViewById<EditText>(R.id.text_item_count)
        itemCount.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f
        )

        builder.setPositiveButton("Add") { dialogInterface: DialogInterface, _ ->
            if (TextHelper().checkInputs(itemName.text.toString(), itemCount.text.toString())) {
                val item = Item(itemName.text.toString(), itemCount.text.toString().toInt(), false)
                var listJson = args.shoplist.items
                val listItems = JSONUtil().readFromJSON(listJson)
                val mutableListItems = listItems.toMutableList()
                mutableListItems.add(item)
                listJson = JSONUtil().generateJSONString(mutableListItems)

                //todo update view after added items - still not working ;c - problem is a 105 line
                // data is read from args. After every action this args are not updated

                viewModel.updateShopList(args.shoplist.id, listJson)
                adapter.notifyDataSetChanged()
            } else
                Toast.makeText(
                    context,
                    "Can't add empty data. Please fill all inputs",
                    Toast.LENGTH_LONG
                ).show()
        }

        builder.setNegativeButton("Cancel") { dialogInterface: DialogInterface, _ ->
            dialogInterface.cancel()
        }
        builder.create().show()
    }
}