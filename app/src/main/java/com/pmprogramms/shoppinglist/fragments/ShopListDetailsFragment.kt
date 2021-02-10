package com.pmprogramms.shoppinglist.fragments

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.viewmodel.ShopListViewModel
import com.pmprogramms.shoppinglist.databinding.FragmentShopListDetailsBinding

class ShopListDetailsFragment : Fragment() {
    lateinit var binding: FragmentShopListDetailsBinding
    private val args by navArgs<ShopListDetailsFragmentArgs>()
    private lateinit var viewModel: ShopListViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentShopListDetailsBinding.inflate(layoutInflater)
        binding.title.text = args.shoplist.title

        viewModel = ViewModelProvider(this).get(ShopListViewModel::class.java)

        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
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
            findNavController().navigate(R.id.action_shopListDetailsFragment_to_mainFragment    )
        }

        builder.setNegativeButton("No") { dialogInterface: DialogInterface, _ ->
            dialogInterface.dismiss()
        }

        builder.setTitle("Delete")
        builder.setMessage("Do you want delete ${args.shoplist.title}")
        builder.create().show()
    }
}