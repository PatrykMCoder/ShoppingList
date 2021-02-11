package com.pmprogramms.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.pmprogramms.shoppinglist.databinding.FragmentArchiveShopListBinding
import com.pmprogramms.shoppinglist.adapters.ListArchiveAdapter
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel

class ArchiveShopListFragment : Fragment() {
    private lateinit var viewModel: ShopListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentArchiveShopListBinding.inflate(layoutInflater)

        viewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)

        val adapter = ListArchiveAdapter()

        binding.recycler.adapter = adapter
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())

        viewModel.readAllArchiveData.observe(viewLifecycleOwner, Observer { shopList ->
            adapter.setData(shopList)
        })

        return binding.root
    }
}