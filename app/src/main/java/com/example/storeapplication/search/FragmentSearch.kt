package com.example.storeapplication.search

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.databinding.FragmentSearchBinding
import com.example.storeapplication.favourite.data.Favourite
import com.example.storeapplication.favourite.ui.ClickListener
import com.example.storeapplication.favourite.ui.FavouriteViewModel
import com.example.storeapplication.home.ui.HomeViewModel
import java.util.*
import kotlin.collections.ArrayList

class FragmentSearch : Fragment(), ClickListener, SearchView.OnQueryTextListener {
    lateinit var binding: FragmentSearchBinding
    private var productsList = arrayListOf<Product>()
    private var tempList = arrayListOf<Product>()
    private val homeViewModel: HomeViewModel by viewModels()
    private val favouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getProductsFromApI()
        binding.searchView.setOnQueryTextListener(this)

    }

    private fun getProductsFromApI() {
        homeViewModel.getProducts()
        homeViewModel.productList.observe(viewLifecycleOwner) {
            it?.let { productsList.addAll(it) }
            tempList.addAll(productsList)
            showProductsOnRecyclerView(productsList)
            Log.i(TAG, "getProductsFromApI: $it")
        }
    }

    private fun showProductsOnRecyclerView(productsList: ArrayList<Product>) {
        val adapterSearchView = SearchViewAdapter(productsList, this)
        binding.searchRecyclerView.adapter = adapterSearchView
    }


    override fun onFavouriteIconClick(favourite: Favourite) {
//        favouriteViewModel.insertFavourite(favourite)
    }

    override fun onProductClick(productID: Int) {
        findNavController().navigate(FragmentSearchDirections.actionFragmentSearchToDeatilesFragment(productID))
    }

    override fun onQueryTextSubmit(query: String?): Boolean { return false }

    override fun onQueryTextChange(newText: String?): Boolean {
        tempList.clear()
        val searchText = newText?.lowercase(Locale.getDefault())
        if (searchText?.isNotEmpty() == true) {
            productsList.forEach { productItem ->
                if (productItem.title.lowercase(Locale.getDefault()).contains(searchText)) {
                    tempList.add(productItem)
                }
            }
            binding.searchRecyclerView.adapter?.notifyDataSetChanged()
            showProductsOnRecyclerView(tempList)
        } else {
            tempList.clear()
            tempList.addAll(productsList)
            binding.searchRecyclerView.adapter?.notifyDataSetChanged()
        }
        return false
    }
}

