package com.example.storeapplication

import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.Transliterator
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.app.Person.fromBundle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.databinding.FragmentDeatilesBinding
import com.example.storeapplication.databinding.ProductsItemUiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.storeapplication.GetProductResponseItem as GetProductResponseItem1
import kotlin.collections.MutableList as MutableList1


class DeatilesFragment : Fragment() {

    lateinit var binding: FragmentDeatilesBinding
    lateinit var productsRVAdapter: ProductsRVAdapter
    lateinit var ratingBar: RatingBar
    lateinit var addbutton: Button
    lateinit var productdescription: TextView
    lateinit var ProductsList:MutableList1<GetProductResponseItem1>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDeatilesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProductsFromApI()


    }


    private fun getProductsFromApI() {
        RetrofitClient.getClient().getProducts().enqueue(object :
            Callback<kotlin.collections.MutableList<com.example.storeapplication.GetProductResponseItem>> {
            override fun onResponse(
                call: Call<kotlin.collections.MutableList<com.example.storeapplication.GetProductResponseItem>>,
                response: Response<kotlin.collections.MutableList<com.example.storeapplication.GetProductResponseItem>>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: " + response.body())

                }
            }

            override fun onFailure(
                call: Call<kotlin.collections.MutableList<com.example.storeapplication.GetProductResponseItem>>,
                t: Throwable
            ) {
                Log.i(TAG, "onFailure: " + t.localizedMessage)
            }

        })


    }
}




