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
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.data.json.Item
import com.pmprogramms.shoppinglist.databinding.FragmentAddNewBinding
import com.pmprogramms.shoppinglist.util.JSONUtil
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
            insetDataToDB();
        }

        createElements() // here is create first element
        return binding.root
    }

    private fun insetDataToDB() {
        collectDataFromUI()
        val title = binding.textTitle.text.toString()

        val now = Calendar.getInstance().timeInMillis
        val jsonString = JSONUtil().generateJSONString(list)
        val shopList = ShopList(0, title, false, jsonString, now)
        viewModel.addShopList(shopList)

        parentFragmentManager.popBackStack() // after saving fragment call back to HomeFragment
    }

    private fun collectDataFromUI() {
        var tmp = 0
        while (tmp != createdElement) {
            val newShopItem: EditText = box.findViewWithTag("t_$tmp")
            val itemCount = 0
            val itemCollect = false
            val item = Item(newShopItem.text.toString(), itemCount, itemCollect)
            list.add(item)
            tmp++
        }

    }

    //todo create edittext for inset counts element of one item
    private fun createElements() {
        val linearLayout = LinearLayout(context)
        linearLayout.layoutParams =
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        linearLayout.orientation = LinearLayout.HORIZONTAL
        val newShopItem = EditText(context)
        newShopItem.hint = "Enter item"
        newShopItem.setBackgroundColor(Color.TRANSPARENT)
        newShopItem.textSize = 20F
        newShopItem.maxLines = 1
        newShopItem.inputType = InputType.TYPE_CLASS_TEXT
        newShopItem.setTextColor(Color.BLACK)
        newShopItem.requestFocus()
        newShopItem.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_ENTER) {
                createElements()
                return@setOnKeyListener true
            }
            false
        }

        newShopItem.tag = "t_$createdElement"
        linearLayout.addView(newShopItem)
        box.addView(linearLayout)
        createdElement++
    }
}