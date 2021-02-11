package com.pmprogramms.shoppinglist.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.adapters.ListAdapter
import com.pmprogramms.shoppinglist.databinding.FragmentShopListBinding
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel

class ShopListFragment : Fragment() {
    private lateinit var viewModel: ShopListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentShopListBinding.inflate(inflater)
        binding.add.setOnClickListener {
           findNavController().navigate(R.id.action_mainFragment_to_addNewFragment2)
        }

        val adapter = ListAdapter()
        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)
        viewModel.readAllUnArchiveData.observe(viewLifecycleOwner, { shopList ->
            adapter.setData(shopList)
        })

        return binding.root
    }
}