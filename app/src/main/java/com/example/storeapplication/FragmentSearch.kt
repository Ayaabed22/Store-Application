package com.example.storeapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.databinding.FragmentHomeBinding
import com.example.storeapplication.databinding.FragmentSearchBinding
import com.example.storeapplication.productDetails.ProductClick
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentSearch : Fragment(),ProductClick{
    lateinit var binding: FragmentSearchBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?)
    : View? {
        binding =FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

         getProductsFromApI()

    }
    private fun getProductsFromApI() {
        RetrofitClient.getClient().getProducts()
            .enqueue(object : Callback<MutableList<GetProductResponseItem>> {
                override fun onResponse(
                    call: Call<MutableList<GetProductResponseItem>>,
                    response: Response<MutableList<GetProductResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        showProductsOnRecyclerView(response)
                        Log.i(TAG, "onResponse: " + response.body())
                    }
                }

                override fun onFailure(
                    call: Call<MutableList<GetProductResponseItem>>,
                    t: Throwable
                ) {
                    Log.i(TAG, "onFailure: " + t.localizedMessage)
                }

            })
    }

    private fun showProductsOnRecyclerView(response: Response<MutableList<GetProductResponseItem>>) {

        val adapterSearchView=
          AdaterSearchview (response.body() as MutableList<GetProductResponseItem>,this)
        binding.sreachRecyclerView.adapter=adapterSearchView

    }

    override fun itemClick(productId: Int){
        val action=FragmentSearchDirections.actionFragmentSearchToDeatilesFragment(productId)
        findNavController().navigate(action)
    }


}

