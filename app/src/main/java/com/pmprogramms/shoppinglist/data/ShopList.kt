package com.pmprogramms.shoppinglist.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "shopping_list_table")
data class ShopList(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val archive: Boolean,
    val items: String, // here will be put json. I don't know that is good idea, but for now I can't invent better way
    val createdAt: Long,
): Parcelable