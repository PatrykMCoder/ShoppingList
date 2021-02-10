package com.pmprogramms.shoppinglist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.pmprogramms.shoppinglist.databinding.ActivityMainBinding
import com.pmprogramms.shoppinglist.fragments.ArchiveShopListFragment
import com.pmprogramms.shoppinglist.fragments.MainFragment
import com.pmprogramms.shoppinglist.fragments.ShopListFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, backStack:Boolean) {
    val fragmentManager = supportFragmentManager
    val transaction = fragmentManager.beginTransaction()
    transaction.replace(R.id.container, fragment)
    if (backStack) transaction.addToBackStack(null)
    transaction.commit()
}