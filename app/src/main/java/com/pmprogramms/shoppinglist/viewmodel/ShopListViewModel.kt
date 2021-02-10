package com.pmprogramms.shoppinglist.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.data.ShopListDatabase
import com.pmprogramms.shoppinglist.repository.ShopListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShopListViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<ShopList>>
    private val repository: ShopListRepository

    init {
        val shopListDao = ShopListDatabase.getDatabase(application).shopListDao()
        repository = ShopListRepository(shopListDao)
        readAllData = repository.readAllData
    }

    fun addShopList(shopList: ShopList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addShopList(shopList)
        }
    }

    fun deleteSingleList(shopList: ShopList) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteSingleList(shopList)
        }
    }
}