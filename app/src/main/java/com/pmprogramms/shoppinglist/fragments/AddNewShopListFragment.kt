package com.pmprogramms.shoppinglist.fragments

import android.graphics.Color
import android.os.Bundle
import android.text.InputType
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.data.json.Item
import com.pmprogramms.shoppinglist.databinding.FragmentAddNewBinding
import com.pmprogramms.shoppinglist.util.json.JSONUtil
import com.pmprogramms.shoppinglist.util.text.TextHelper
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel
import java.util.*
import kotlin.collections.ArrayList


class AddNewShopListFragment : Fragment() {

    private lateinit var viewModel: ShopListViewModel
    private lateinit var binding: FragmentAddNewBinding
    private lateinit var box: LinearLayout
    private var createdElement: Int = 0
    private var list: ArrayList<Item> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        binding = FragmentAddNewBinding.inflate(layoutInflater)
        box = binding.box

        binding.boxNewItem.setOnClickListener {
            createElements()
        }

        binding.save.setOnClickListener {
            insetDataToDB()
        }

        createElements() // here is create first element
        return binding.root
    }

    private fun insetDataToDB() {
        val title = binding.textTitle.text.toString()
        if (TextHelper().checkTitle(title)) {
            collectDataFromUI()
            val now = Calendar.getInstance().timeInMillis
            val jsonString = JSONUtil().generateJSONString(list)
            val shopList = ShopList(0, title, false, jsonString, now)
            viewModel.addShopList(shopList)

            findNavController().navigate(R.id.action_addNewFragment2_to_mainFragment)
        } else {
            Toast.makeText(context, "Please insert title", Toast.LENGTH_LONG).show()
        }
    }


    private fun collectDataFromUI() {
        var tmp = 0
        while (tmp != createdElement) {
            val newShopItem: EditText = box.findViewWithTag("t_$tmp")
            val newShopItemCount: EditText = box.findViewWithTag("c_$tmp")
            val shopItemName = newShopItem.text.toString()
            val itemCount = newShopItemCount.text.toString()
            val itemCollect = false
            if (TextHelper().checkInputs(shopItemName, itemCount)) {
                val item = Item(newShopItem.text.toString(), itemCount.toInt(), itemCollect)
                list.add(item)
            }
            tmp++
        }
    }

    private fun createElements() {
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        linearLayout.orientation = LinearLayout.HORIZONTAL
        val newShopItem = EditText(context)
        val shopItemCount = EditText(context)

        newShopItem.hint = "Enter item"
        newShopItem.setBackgroundColor(Color.TRANSPARENT)
        newShopItem.textSize = 20F
        newShopItem.maxLines = 1
        newShopItem.inputType = InputType.TYPE_CLASS_TEXT
        newShopItem.setTextColor(Color.BLACK)
        newShopItem.requestFocus()
        newShopItem.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f
        )
        newShopItem.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                createElements()
                return@setOnKeyListener true
            }
            false
        }

        newShopItem.tag = "t_$createdElement"

        shopItemCount.hint = "Enter count item"
        shopItemCount.setBackgroundColor(Color.TRANSPARENT)
        shopItemCount.textSize = 20F
        shopItemCount.maxLines = 1
        shopItemCount.inputType = InputType.TYPE_CLASS_NUMBER
        shopItemCount.setTextColor(Color.BLACK)
        shopItemCount.requestFocus()
        shopItemCount.setPadding(10, 0, 0, 0)
        shopItemCount.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT, 0.5f
        )
        shopItemCount.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                createElements()
                return@setOnKeyListener true
            }
            false
        }

        shopItemCount.tag = "c_$createdElement"

        linearLayout.addView(newShopItem)
        linearLayout.addView(shopItemCount)
        box.addView(linearLayout)
        createdElement++
    }
}