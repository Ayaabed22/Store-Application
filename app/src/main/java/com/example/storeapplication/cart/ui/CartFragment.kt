package com.example.storeapplication.cart.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.storeapplication.GetProductResponseItem
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.cart.data.CartResponse
import com.example.storeapplication.cart.data.ProductsItem
import com.example.storeapplication.databinding.FragmentCartBinding
import com.example.storeapplication.login.SignInFragment
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {
    private val TAG = "CartFragment"
    private lateinit var binding:FragmentCartBinding
    var cartList:MutableList<GetProductResponseItem> = mutableListOf()



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        RetrofitClient.getClient().getUserCarts(3).enqueue(object: Callback<MutableList<CartResponse>>{
            override fun onResponse(
                call: Call<MutableList<CartResponse>>,
                response: Response<MutableList<CartResponse>>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: "+ response.body())

                    response.body()!!.all {
                        Log.i(TAG, "on All :" + it.products)
                        getProductDetails(it.products!!)
                        true
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<CartResponse>>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t.localizedMessage)
                Toast.makeText(requireContext(),"Cart Failure",Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun getProductDetails(productList: List<ProductsItem?>?){
        //crate an empty list of products
        //get All Products from api
        //if response successful create for loop on productList
        //matching id on response by using filter method
        //if the item is found add the item to the empty list
        //outside the for loop return the new list of products

        for (i in productList!!.indices){
            val productID = productList[i]?.productId
            Log.i(TAG, "getProductDetails: $productID")
            val quantity = productList[i]?.quantity

            productID?.let {
                RetrofitClient.getClient().getProductDetails(it).enqueue(object : Callback<GetProductResponseItem> {
                        override fun onResponse(
                            call: Call<GetProductResponseItem>,
                            response: Response<GetProductResponseItem>
                        ) {
                            if (response.isSuccessful) {
                                Log.i(TAG, "onResponse: " + response.body())
                                cartList.add(response.body()?.copy()!!)
                                Log.i(TAG, "NEW CART : $cartList")
                                setDataOnRV(cartList)
                            }
                        }
                        override fun onFailure(call: Call<GetProductResponseItem>, t: Throwable) {
                            Log.i(TAG, "onFailure: " + t.localizedMessage)
                        }
                    })
            }
        }
    }

    private fun setDataOnRV(cartList: MutableList<GetProductResponseItem>) {
        val cartAdapter = CartAdapter(cartList)
        binding.CartRV.adapter = cartAdapter
    }
}
