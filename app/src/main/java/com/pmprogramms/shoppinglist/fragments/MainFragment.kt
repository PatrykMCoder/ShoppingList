package com.pmprogramms.shoppinglist.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.pmprogramms.shoppinglist.R
import com.pmprogramms.shoppinglist.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(layoutInflater)

        val tabLayout = binding.tabLayout
        val pager = binding.pager
        val adapter = PagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(pager)

        pager.adapter = adapter

        tabLayout.getTabAt(0)?.setIcon(R.drawable.ic_baseline_list_24)
        tabLayout.getTabAt(1)?.setIcon(R.drawable.ic_baseline_archive_24)

        return binding.root
    }

    class PagerAdapter(fm: FragmentManager): FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int {
            return 2
        }

        override fun getItem(position: Int): Fragment {
            return when (position) {
                0 -> ShopListFragment()
                1 -> ArchiveShopListFragment()
                else ->  ShopListFragment()
            }
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                0 -> "ShopList"
                1 -> "ArchiveShopList"
                else ->  "ShopList"
            }
        }
    }
}