package com.pmprogramms.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addList(shopList: ShopList)

    @Query("SELECT * FROM shopping_list_table ORDER BY createdAt DESC")
    fun readAllData() : LiveData<List<ShopList>>

    @Delete
    suspend fun deleteSingleList(shopList: ShopList)
}