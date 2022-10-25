package com.example.storeapplication
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.storeapplication.databinding.FragmentSearchBinding
import com.example.storeapplication.productDetails.ProductClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList


class FragmentSearch : Fragment(),ProductClick {
    lateinit var binding: FragmentSearchBinding
     var productsList: ArrayList<GetProductResponseItem> = ArrayList()
    var tempList: ArrayList<GetProductResponseItem> = ArrayList()

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

            override fun onQueryTextChange(newText: String?): Boolean {
                tempList.clear()
                val searchText = newText?.toLowerCase(Locale.getDefault())
                if (searchText != null) {
                    if (searchText.isNotEmpty()){
                        productsList.forEach { productItem->
                            if (productItem.title.toLowerCase(Locale.getDefault()).contains(newText)){
                                tempList.add(productItem)
                            }
                        }
                        showProductsOnRecyclerView(tempList)
                    }
                    else{
                        tempList.clear()
                        tempList.addAll(productsList)
                        showProductsOnRecyclerView(tempList)
                    }
                }
                return false
            }

        })
    }

    private fun getProductsFromApI() {
        RetrofitClient.getClient().getProducts()
            .enqueue(object : Callback<MutableList<GetProductResponseItem>> {
                override fun onResponse(
                    call: Call<MutableList<GetProductResponseItem>>,
                    response: Response<MutableList<GetProductResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        response.body()?.let { productsList.addAll(it) }
                        tempList.addAll(productsList)
                        showProductsOnRecyclerView(productsList)
                        Log.i(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(
                    call: Call<MutableList<GetProductResponseItem>>, t: Throwable
                ) {
                    Log.i(TAG, "onFailure: " + t.localizedMessage)
                }
            })
    }


    private fun showProductsOnRecyclerView(productsList: ArrayList<GetProductResponseItem>) {
        val adapterSearchView = AdaterSearchview(productsList, this)
        binding.sreachRecyclerView.adapter = adapterSearchView
    }

    override fun itemClick(productId: Int) {
        val action = FragmentSearchDirections.actionFragmentSearchToDeatilesFragment(productId)
        findNavController().navigate(action)
    }
}

