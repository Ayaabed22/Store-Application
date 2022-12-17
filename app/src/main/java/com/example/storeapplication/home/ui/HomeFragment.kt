package com.example.storeapplication.home.ui

import android.annotation.SuppressLint
import android.os.Bundle
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
import com.example.storeapplication.favourite.data.Favourite
import com.example.storeapplication.favourite.ui.ClickListener
import com.example.storeapplication.favourite.ui.FavouriteViewModel
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.utils.Const
import com.example.storeapplication.utils.Const.SORT_BY_NAME
import com.example.storeapplication.utils.Const.SORT_BY_PRICE
import com.example.storeapplication.utils.MySharedPreferences
import com.example.storeapplication.utils.MySharedPreferences.KEY_MY_SHARED_BOOLEAN_LOGIN
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout

class HomeFragment : Fragment(),NavigationView.OnNavigationItemSelectedListener,TabLayout.OnTabSelectedListener, ClickListener{

    private lateinit var  binding: FragmentHomeBinding
    private var category = ""
    private val productsAdapter = ProductsAdapter(this)
    private val homeViewModel: HomeViewModel by viewModels()
    private val favouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productsRV.layoutManager = layoutManager
        binding.productsRV.adapter =productsAdapter

        getProducts()
        binding.topAppBar.setOnMenuItemClickListener {
           when (it.itemId) {
               R.id.searchIcon -> navigate(R.id.action_homeFragment_to_fragmentSearch)
               R.id.sortByName-> homeViewModel.sortProducts(SORT_BY_NAME)?.let { sortedList -> updateList(sortedList) }
               R.id.sortByPrice-> homeViewModel.sortProducts(SORT_BY_PRICE)?.let { sortedList -> updateList(sortedList) }
           }
           return@setOnMenuItemClickListener true
       }

        binding.topAppBar.setOnClickListener { openNavigationDrawer() }
        binding.navView.setNavigationItemSelectedListener(this)
        binding.categoryTabs.addOnTabSelectedListener(this)

        setNavigationHeaderData()
        }

    private fun setNavigationHeaderData(){
        val userData = MySharedPreferences.getUserDataFromShared(requireContext())

        val navigationView = binding.navView
        val headerView = navigationView.getHeaderView(0)

        headerView.findViewById<TextView>(R.id.nav_name).text = userData.username
        headerView.findViewById<TextView>(R.id.nav_email).text = userData.email
    }

    private fun getProducts() {
        homeViewModel.productList.observe(viewLifecycleOwner,::updateList)
        homeViewModel.getProducts()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun updateList(itemList: MutableList<Product>){
        with(productsAdapter) {
            setProductList(itemList)
            notifyDataSetChanged()
        }
    }

    private fun openNavigationDrawer() {
        val desiredGravity = GravityCompat.START
        with(binding.drawableLayout) {
            if (this.isDrawerOpen(desiredGravity)) {
                this.closeDrawer(desiredGravity)
            } else {
                this.openDrawer(desiredGravity)
            }
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
        navigate(R.id.action_homeFragment_to_signin)
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
            getProducts()}
        when(tab?.position){
            1-> category = Const.MEN_CATEGORY
            2-> category = Const.WOMEN_CATEGORY
            3-> category = Const.JEWELERY_CATEGORY
            4-> category = Const.ELECTRONICS_CATEGORY
        }
        productsInSpecificCategory(category)
    }

    private fun productsInSpecificCategory(category: String) {
        homeViewModel.getProductsInSpecificCategory(categoryName = category)
        homeViewModel.productList.observe(viewLifecycleOwner,::updateList)
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {}

    override fun onTabReselected(tab: TabLayout.Tab?) {}

    override fun onFavouriteIconClick(favourite: Favourite) {
        favouriteViewModel.updateFavouriteList(favourite)
    }

    override fun onProductClick(productID: Int) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDeatilesFragment(productID))
    }

    private fun navigate(destination: Int) = view?.findNavController()?.navigate(destination)
}
