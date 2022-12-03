package com.example.storeapplication.search
import android.annotation.SuppressLint
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
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.databinding.FragmentSearchBinding
import com.example.storeapplication.favourite.ui.ItemClick
import com.example.storeapplication.home.ui.HomeViewModel
import java.util.*
import kotlin.collections.ArrayList


class FragmentSearch : Fragment(),ItemClick {
    lateinit var binding: FragmentSearchBinding
    var productsList = arrayListOf<GetProductResponseItem>()
    var tempList= arrayListOf<GetProductResponseItem>()
    private val homeViewModel:HomeViewModel by viewModels()

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
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
               return  false
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun onQueryTextChange(newText: String?): Boolean {
                tempList.clear()
                val searchText = newText?.lowercase(Locale.getDefault())
                    if (searchText?.isNotEmpty() == true){
                        productsList.forEach { productItem->
                            if (productItem.title.lowercase(Locale.getDefault()).contains(searchText)){
                                tempList.add(productItem)
                            }
                        }
                        binding.searchRecyclerView.adapter?.notifyDataSetChanged()
                        showProductsOnRecyclerView(tempList)
                    }
                    else{
                        tempList.clear()
                        tempList.addAll(productsList)
                        binding.searchRecyclerView.adapter?.notifyDataSetChanged()
                    }
                return false
            }

        })
    }

    private fun getProductsFromApI() {
        homeViewModel.getProducts()
        homeViewModel.itemList.observe(viewLifecycleOwner) {
            it?.let { productsList.addAll(it) }
            tempList.addAll(productsList)
            showProductsOnRecyclerView(productsList)
            Log.i(TAG, "getProductsFromApI: $it")
        }
    }


    private fun showProductsOnRecyclerView(productsList: ArrayList<GetProductResponseItem>) {
        val adapterSearchView = SearchViewAdapter(productsList, this)
        binding.searchRecyclerView.adapter = adapterSearchView
    }


    override fun favouriteClickListener(id: Int, name: String, price: Double, image: String) {
    }

    override fun productClickListener(id: Int) {
        val action = FragmentSearchDirections.actionFragmentSearchToDeatilesFragment(id)
        findNavController().navigate(action)
     }
}

