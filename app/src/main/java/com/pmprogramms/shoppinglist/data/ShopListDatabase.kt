package com.pmprogramms.shoppinglist.data

import android.content.Context
import androidx.room.*

@Database(entities = [ShopList::class], version = 1, exportSchema = false)
abstract class ShopListDatabase : RoomDatabase() {
    abstract fun shopListDao(): ShopListDao

    companion object {
        @Volatile
        private var INSTANCE: ShopListDatabase? = null

        fun getDatabase(context: Context): ShopListDatabase {
            val tmpInst = INSTANCE
            if (tmpInst != null)
                return tmpInst

            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ShopListDatabase::class.java,
                    "shopping_list_table").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
