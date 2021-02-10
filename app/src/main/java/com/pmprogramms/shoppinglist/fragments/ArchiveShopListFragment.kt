package com.pmprogramms.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.pmprogramms.shoppinglist.R
class ArchiveShopListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        Toast.makeText(context, "pa", Toast.LENGTH_LONG).show()
        return inflater.inflate(R.layout.fragment_archive_shop_list, container, false)
    }
}