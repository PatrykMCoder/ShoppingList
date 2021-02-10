package com.pmprogramms.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel
import com.pmprogramms.shoppinglist.databinding.FragmentAddNewBinding
import java.util.*

class AddNewShopListFragment : Fragment() {

    private lateinit var viewModel: ShopListViewModel
    private lateinit var binding: FragmentAddNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)

        binding = FragmentAddNewBinding.inflate(layoutInflater)

        binding.save.setOnClickListener {
            insetDataToDB();
        }

        return binding.root
    }

    private fun insetDataToDB() {
        //add validation
        val title = binding.textTitle.text.toString()
        val item = binding.textItem.text.toString()

        val now = Calendar.getInstance().timeInMillis
        val shopList = ShopList(0, title, false, item, now)
        viewModel.addShopList(shopList)

        parentFragmentManager.popBackStack() // after saving fragment call back to HomeFragment
    }
}