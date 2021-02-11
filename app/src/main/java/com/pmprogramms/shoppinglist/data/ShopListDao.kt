package com.pmprogramms.shoppinglist.data

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ShopListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addList(shopList: ShopList)

    @Query("SELECT * FROM shopping_list_table ORDER BY createdAt DESC")
    fun readAllData(): LiveData<List<ShopList>>

    @Query("SELECT * FROM shopping_list_table WHERE archive=0 ORDER BY createdAt DESC")
    fun readAllDataUnArchive(): LiveData<List<ShopList>>

    @Query("SELECT * FROM shopping_list_table WHERE archive=1 ORDER BY createdAt DESC")
    fun readAllDataArchive(): LiveData<List<ShopList>>

    @Delete
    suspend fun deleteSingleList(shopList: ShopList)

    @Query("UPDATE shopping_list_table SET items = :jsonString WHERE id = :id")
    suspend fun updateShopList(id: Int, jsonString: String)

    @Query("UPDATE shopping_list_table SET archive = 1 where id = :id")
    suspend fun updateShopArchiveAction(id: Int)

    @Query("UPDATE shopping_list_table SET archive = 0 where id = :id")
    suspend fun updateShopUnArchiveAction(id: Int)
}