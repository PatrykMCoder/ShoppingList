package com.pmprogramms.shoppinglist.repository

import androidx.lifecycle.LiveData
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.data.ShopListDao

class ShopListRepository(
    private val shopListDao: ShopListDao
) {
    val readAllData: LiveData<List<ShopList>> = shopListDao.readAllData()
    val readAllUnArchiveData: LiveData<List<ShopList>> = shopListDao.readAllDataUnArchive()
    val readAllArchiveData: LiveData<List<ShopList>> = shopListDao.readAllDataArchive()

    suspend fun addShopList(shopList: ShopList) {
        shopListDao.addList(shopList)
    }

    suspend fun deleteSingleList(shopList: ShopList) {
        shopListDao.deleteSingleList(shopList)
    }

    suspend fun archiveAction(id: Int) {
        shopListDao.updateShopArchiveAction(id)
    }

    suspend fun unArchiveAction(id: Int) {
        shopListDao.updateShopUnArchiveAction(id)
    }

    suspend fun updateShopList(id: Int,  jsonString: String) {
        shopListDao.updateShopList(id, jsonString)
    }
}