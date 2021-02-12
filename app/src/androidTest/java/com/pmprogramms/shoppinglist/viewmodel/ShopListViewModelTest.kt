package com.pmprogramms.shoppinglist.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.pmprogramms.shoppinglist.data.ShopList
import com.pmprogramms.shoppinglist.data.ShopListDao
import com.pmprogramms.shoppinglist.data.ShopListDatabase
import com.pmprogramms.shoppinglist.getOrAwaitValue
import org.hamcrest.CoreMatchers.not
import org.hamcrest.core.IsNull.nullValue
import org.junit.After
import org.junit.Before

import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ShopListViewModelTest {
    private lateinit var viewModel: ShopListViewModel
    private lateinit var database: ShopListDatabase
    private lateinit var dao: ShopListDao
    private lateinit var shopList: ShopList

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = ShopListViewModel(ApplicationProvider.getApplicationContext())
        database = ShopListDatabase.getDatabase(ApplicationProvider.getApplicationContext())
        dao = database.shopListDao()
        shopList = ShopList(id = 1, "test", false, "", 1000000L)
    }

    @After
    fun tearDown() {

    }

    @Test
    fun addNewElementTest() {

        viewModel.addShopList(shopList)
        val shopListViewModel = ShopListViewModel(ApplicationProvider.getApplicationContext())

        shopListViewModel.addShopList(shopList)

        val value = shopListViewModel.readAllData.getOrAwaitValue()

        assertThat(value[0], not(nullValue()))
    }

    @Test
    fun updateElementTest() {
        val jsonString = ""
        val shopListViewModel = ShopListViewModel(ApplicationProvider.getApplicationContext())

        shopListViewModel.updateShopList(id=1, jsonString)

        val value = shopListViewModel.readAllData.getOrAwaitValue()

        assertThat(value[0], not(nullValue()))
    }

    @Test
    fun deleteElementTest() {
        val shopListViewModel = ShopListViewModel(ApplicationProvider.getApplicationContext())

        shopListViewModel.deleteSingleList(shopList)

        val value = shopListViewModel.readAllData.getOrAwaitValue()

        assertThat(value[0], not(nullValue()))
    }
}