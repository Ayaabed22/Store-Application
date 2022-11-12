package com.example.storeapplication.cart.ui

import android.content.Context
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
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.cart.data.ProductsItem
import com.example.storeapplication.databinding.FragmentCartBinding
import com.example.storeapplication.utils.MySharedPreferences
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CartFragment : Fragment() {
    private val TAG = "CartFragment"
    private lateinit var binding:FragmentCartBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = getIdFromShared()

        if (id != null) {
            RetrofitClient.getClient().getUserCarts(id).enqueue(object: Callback<MutableList<CartResponse>>{
                override fun onResponse(
                    call: Call<MutableList<CartResponse>>,
                    response: Response<MutableList<CartResponse>>
                ) {
                    if (response.isSuccessful) {
                        Log.i(TAG, "onResponse: "+ response.body())
                        response.body()?.let {responseBody->
                            val cartItems = buildList {
                                responseBody.forEach {
                                    it.products?.let { it1 -> addAll(it1.toMutableList()) }
                                }
                            }
                            getProductDetails(cartItems)
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
    }

    private fun getIdFromShared(): Int? {
        MySharedPreferences.getPrefs(requireContext())
        val json= MySharedPreferences.getString(requireContext(), MySharedPreferences.KEY_MY_SHARED_String)
        val gson = Gson()
        val obj: GetAllUsersResponse? = gson.fromJson(json, GetAllUsersResponse::class.java)
        return obj?.id
    }

    private fun getProductDetails(cartItems: List<ProductsItem?>?){
        //crate an empty list of products
        //get All Products from api
        //if response successful create for loop on productList
        //matching id on response by using filter method
        //if the item is found add the item to the empty list
        //outside the for loop return the new list of products
        val emptyList:ArrayList<GetProductResponseItem> = ArrayList()

        RetrofitClient.getClient().getProducts().enqueue(object : Callback<MutableList<GetProductResponseItem>> {
                override fun onResponse(
                    call: Call<MutableList<GetProductResponseItem>>,
                    response: Response<MutableList<GetProductResponseItem>>
                ) {
                    if (response.isSuccessful) {
                        Log.i(TAG, "onResponse: " + response.body())
                        val allProductsList = response.body()
                        cartItems?.let {cartItems->
                            for (cartItem in cartItems) {
                               val newCartItem =  allProductsList?.find { it.id == cartItem?.productId }?.copy(quantity = cartItem?.quantity)
                               newCartItem?.let {
                                emptyList.add(it)
                            }
                            }
                            setDataOnRV(emptyList)
                        }
                    }
                }
                override fun onFailure(
                    call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                    Log.i(TAG, "onFailure: " + t.localizedMessage)
                }
            })
    }

    private fun setDataOnRV(cartList: MutableList<GetProductResponseItem>) {
        val cartAdapter = CartAdapter(cartList)
        binding.CartRV.adapter = cartAdapter
    }
}
