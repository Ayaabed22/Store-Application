package com.example.storeapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.databinding.FragmentHomeBinding
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(),NavigationView.OnNavigationItemSelectedListener,TabLayout.OnTabSelectedListener{

    private val TAG = "HomeFragment"
    private lateinit var  binding: FragmentHomeBinding
    var category: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setNavigationOnClickListener{
            openNavigationDrawer()
        }
        getProductsFromApI()

        binding.navView.setNavigationItemSelectedListener(this)

        binding.categoryTabs.addOnTabSelectedListener(this)

        }

    private fun getProductsFromApI() {
        RetrofitClient.getClient().getProducts().enqueue(object: Callback<MutableList<GetProductResponseItem>> {
            override fun onResponse(
                call: Call<MutableList<GetProductResponseItem>>,
                response: Response<MutableList<GetProductResponseItem>>
            ) {
                if (response.isSuccessful) {
                    showProductsOnRecyclerView(response)
                    Log.i(TAG, "onResponse: "+ response.body())
                }
            }

            override fun onFailure(call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t.localizedMessage)
            }
        })
    }

    private fun showProductsOnRecyclerView(response: Response<MutableList<GetProductResponseItem>>) {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productsRV.layoutManager = layoutManager
        val productsRVAdapter = ProductsRVAdapter(response.body() as MutableList<GetProductResponseItem>)
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
            R.id.nav_cart->
            {
                view?.findNavController()?.navigate(R.id.action_homeFragment_to_cartFragment)

            }
            R.id.nav_favorite->
            {
                Toast.makeText(requireContext(),"Favourite item",Toast.LENGTH_LONG).show()
                Log.i(TAG, "onNavigationItemSelected: " + "favourite item")
            }
            R.id.nav_profile->
            {
                Toast.makeText(requireContext(),"Profile item",Toast.LENGTH_LONG).show()
                Log.i(TAG, "onNavigationItemSelected: " + "Profile item")
            }
        }
        binding.drawableLayout.close()
        return true
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
        RetrofitClient.getClient().getProductsInSpecificCategory(category).enqueue(object: Callback<MutableList<GetProductResponseItem>>{
            override fun onResponse(
                call: Call<MutableList<GetProductResponseItem>>,
                response: Response<MutableList<GetProductResponseItem>>
            ) {
                if (response.isSuccessful){
                    Log.i(TAG, "onResponse: "+response.body())
                    showProductsOnRecyclerView(response)
                }
            }
            override fun onFailure(call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t.localizedMessage)
            }
        })
    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {
        Log.i(TAG, "onTabUnselected: ")
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {
        Log.i(TAG, "onTabReselected: ")
    }
}