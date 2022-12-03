package com.example.storeapplication.home.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.ProductsAPI
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.R
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.databinding.FragmentHomeBinding
import com.example.storeapplication.favourite.ui.ItemClick
import com.example.storeapplication.favourite.data.FavouriteDatabase
import com.example.storeapplication.favourite.data.FavouriteModel
import com.example.storeapplication.utils.Const.Companion.favouriteDao
import com.example.storeapplication.utils.MySharedPreferences
import com.example.storeapplication.utils.MySharedPreferences.KEY_MY_SHARED_BOOLEAN_LOGIN
import com.example.storeapplication.utils.MySharedPreferences.KEY_MY_SHARED_String
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(),NavigationView.OnNavigationItemSelectedListener,TabLayout.OnTabSelectedListener, ItemClick{

    private lateinit var  binding: FragmentHomeBinding
    private var category: String = ""
    private var itemList = mutableListOf<GetProductResponseItem>()
    private val homeViewModel: HomeViewModel by viewModels()
    private val productsCategoryViewModel: ProductsCategoryViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MySharedPreferences.getPrefs(requireContext())

        binding.topAppBar.setOnMenuItemClickListener {
           when (it.itemId) {
               R.id.searchIcon -> {
                   findNavController().navigate(R.id.action_homeFragment_to_fragmentSearch)
               }
               R.id.sort -> {
                   sortItems()
               }
           }
           return@setOnMenuItemClickListener true
       }

        binding.topAppBar.setOnClickListener { openNavigationDrawer() }

        getProductsFromApI()

        binding.navView.setNavigationItemSelectedListener(this)

        binding.categoryTabs.addOnTabSelectedListener(this)

        getUserDataFromShared()

        }

    private fun getUserDataFromShared(){
        val gson = Gson()
        val json = MySharedPreferences.getString(requireContext(),KEY_MY_SHARED_String)
        val obj = gson.fromJson(json, GetAllUsersResponse::class.java)

        val navigationView = binding.navView
        val headerView = navigationView.getHeaderView(0)

        headerView.findViewById<TextView>(R.id.nav_name).text = obj.username
        headerView.findViewById<TextView>(R.id.nav_email).text = obj.email
    }

    private fun sortItems() {
        itemList.sortBy {it.price}
        showProductsOnRecyclerView(itemList)
    }

    private fun getProductsFromApI() {
        homeViewModel.getProducts()
        homeViewModel.itemList.observe(viewLifecycleOwner,::showProductsOnRecyclerView)
    }

    private fun showProductsOnRecyclerView(itemList: MutableList<GetProductResponseItem>) {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productsRV.layoutManager = layoutManager
        val productsRVAdapter = ProductsRVAdapter(itemList,this)
        binding.productsRV.adapter = productsRVAdapter
    }

    private fun openNavigationDrawer() {
        if (binding.drawableLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawableLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawableLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_cart ->
            {
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_cartFragment)

            }
            R.id.nav_favorite ->
            {
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_favouriteFragment)
            }
            R.id.nav_profile ->
            {
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_profileFragment)
            }
            R.id.nav_logout ->
            {
                showAlertDialog()
            }
        }
        binding.drawableLayout.close()
        return true
    }

    private fun logout() {
        MySharedPreferences.saveBooleanLogin(requireContext(),KEY_MY_SHARED_BOOLEAN_LOGIN,false)
        findNavController().popBackStack()
    }

    private fun showAlertDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(resources.getString(R.string.dialogMassageLogout))
            .setNegativeButton(resources.getString(R.string.decline)) { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                logout()
            }.show()
    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        if (tab?.position == 0){
            category = ""
            getProductsFromApI()}
        when(tab?.position){
            1-> category = "men's clothing"
            2-> category = "women's clothing"
            3-> category = "jewelery"
            4-> category = "electronics"
        }
        productsInSpecificCategory(category)
    }

    private fun productsInSpecificCategory(category: String) {
        productsCategoryViewModel.productsInSpesificCategory(categoryName = category)
        productsCategoryViewModel.itemList.observe(viewLifecycleOwner,::showProductsOnRecyclerView)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        Log.i(TAG, "onTabUnselected: ")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        Log.i(TAG, "onTabReselected: ")
    }

    override fun favouriteClickListener(id: Int, name: String, price: Double, image: String) {
        favouriteDao = FavouriteDatabase.getDatabaseInstance(requireContext()).favouriteDao()
        favouriteDao.insertItem(FavouriteModel(id,name,price,image))
    }

    override fun productClickListener(id: Int) {
        val action= HomeFragmentDirections.actionHomeFragmentToDeatilesFragment(id)
        findNavController().navigate(action)
    }

    companion object {
        const val TAG = "HomeFragment"
    }
}