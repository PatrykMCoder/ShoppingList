package com.pmprogramms.shoppinglist.repository

import androidx.lifecycle.LiveData
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.data.ShopListDao

class ShopListRepository(
    private val shopListDao: ShopListDao
) {
    val readAllData: LiveData<List<ShopList>> = shopListDao.readAllData()

    suspend fun addShopList(shopList: ShopList) {
        shopListDao.addList(shopList)
    }

    suspend fun deleteSingleList(shopList: ShopList) {
        shopListDao.deleteSingleList(shopList)
    }
}