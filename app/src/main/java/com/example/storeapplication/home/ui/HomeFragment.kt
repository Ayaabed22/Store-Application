package com.example.storeapplication.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentHomeBinding
import com.example.storeapplication.favourite.data.FavouriteDatabase
import com.example.storeapplication.favourite.data.FavouriteModel
import com.example.storeapplication.favourite.ui.ItemClick
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.utils.Const
import com.example.storeapplication.utils.Const.Companion.favouriteDao
import com.example.storeapplication.utils.MySharedPreferences
import com.example.storeapplication.utils.MySharedPreferences.KEY_MY_SHARED_BOOLEAN_LOGIN
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout



class HomeFragment : Fragment(),NavigationView.OnNavigationItemSelectedListener,TabLayout.OnTabSelectedListener, ItemClick{

    private lateinit var  binding: FragmentHomeBinding
    private var category: String = ""
    private val homeViewModel: HomeViewModel by viewModels()
    private val tagHome = "Home Fragment"
    private val sortByPrice:Int = 1
    private val sortByName:Int = 2

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    @SuppressLint("RestrictedApi")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MySharedPreferences.getPrefs(requireContext())

        binding.topAppBar.setOnMenuItemClickListener {
           when (it.itemId) {
               R.id.searchIcon -> navigate(R.id.action_homeFragment_to_fragmentSearch)

               R.id.sortByName-> {
                   Log.i(tagHome, "onOptionsItemSelected: "+ "Sort by name")
                   sortItems(sortByName)
               }
               R.id.sortByPrice-> {
                   Log.i(tagHome, "onOptionsItemSelected: "+ "Sort by Price")
                   sortItems(sortByPrice)
               }
           }
           return@setOnMenuItemClickListener true
       }

        binding.topAppBar.setOnClickListener { openNavigationDrawer() }

        getProductsFromApI()

        binding.navView.setNavigationItemSelectedListener(this)

        binding.categoryTabs.addOnTabSelectedListener(this)

        setNavigationHeaderData()

        }

    private fun setNavigationHeaderData(){
        val obj = MySharedPreferences.getUserDataFromShared(requireContext())

        val navigationView = binding.navView
        val headerView = navigationView.getHeaderView(0)

        headerView.findViewById<TextView>(R.id.nav_name).text = obj.username
        headerView.findViewById<TextView>(R.id.nav_email).text = obj.email
    }

    private fun sortItems(label:Int) {
        homeViewModel.itemList.observe(viewLifecycleOwner) {
            if (label == sortByName){
                it.sortBy { item-> item.title }
            }
            else if(label == sortByPrice)
            {
                it.sortBy { item-> item.price }
            }
            showProductsOnRecyclerView(it)
        }

    }

    private fun getProductsFromApI() {
        homeViewModel.getProducts()
        homeViewModel.itemList.observe(viewLifecycleOwner,::showProductsOnRecyclerView)
    }

    private fun showProductsOnRecyclerView(itemList: MutableList<GetProductResponseItem>) {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productsRV.layoutManager = layoutManager
        val productsAdapter = ProductsAdapter(itemList,this)
        binding.productsRV.adapter = productsAdapter
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
            R.id.nav_cart -> navigate(R.id.action_homeFragment_to_cartFragment)

            R.id.nav_favorite -> navigate(R.id.action_homeFragment_to_favouriteFragment)

            R.id.nav_profile -> navigate(R.id.action_homeFragment_to_profileFragment)

            R.id.nav_logout -> showAlertDialog()
        }
        binding.drawableLayout.close()
        return true
    }

    private fun logout() {
        MySharedPreferences.saveBoolean(requireContext(),KEY_MY_SHARED_BOOLEAN_LOGIN,false)
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
            1-> category = Const.mensCategory
            2-> category = Const.womenCategory
            3-> category = Const.jeweleryCategory
            4-> category = Const.electronicsCategory
        }
        productsInSpecificCategory(category)
    }

    private fun productsInSpecificCategory(category: String) {
        homeViewModel.productsInSpecificCategory(categoryName = category)
        homeViewModel.itemList.observe(viewLifecycleOwner,::showProductsOnRecyclerView)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        Log.i(tagHome, "onTabUnselected: ")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        Log.i(tagHome, "onTabReselected: ")
    }

    override fun favouriteClickListener(id: Int, name: String, price: Double, image: String) {
        favouriteDao = FavouriteDatabase.getDatabaseInstance(requireContext()).favouriteDao()
        favouriteDao.insertItem(FavouriteModel(id,name,price,image))
    }

    override fun productClickListener(id: Int) {
        val action= HomeFragmentDirections.actionHomeFragmentToDeatilesFragment(id)
        findNavController().navigate(action)
    }

    private fun navigate(destination: Int) = view?.findNavController()?.navigate(destination)

}