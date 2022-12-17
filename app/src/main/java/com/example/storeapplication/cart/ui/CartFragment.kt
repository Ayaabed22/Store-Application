package com.example.storeapplication.cart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.cart.data.Products
import com.example.storeapplication.databinding.FragmentCartBinding
import com.example.storeapplication.home.ui.HomeViewModel
import com.example.storeapplication.utils.MySharedPreferences

class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private var cartAdapter = CartAdapter()
    private val cartViewModel: CartViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = getUserIdFromSharedPref()
        binding.CartRV.adapter = cartAdapter

        if (userId != null) {
            cartViewModel.getUserCart(userId)
            cartViewModel.cartItems.observe(viewLifecycleOwner,::getProductDetails)
        }
    }

    private fun getUserIdFromSharedPref() = MySharedPreferences.getUserDataFromShared(requireContext()).id

    private fun getProductDetails(cartItems: List<Products?>?){
        val cartList:ArrayList<Product> = ArrayList()

        homeViewModel.getProducts()
        homeViewModel.productList.observe(viewLifecycleOwner) { allProductsList ->
            cartItems?.forEach { cartItem ->
            val newCartItem = allProductsList?.find { it.id == cartItem?.productId }?.copy(quantity = cartItem?.quantity)
            newCartItem?.let { cartList.add(it) }
            }
            setCartRVData(cartList)
            }
        }

    private fun setCartRVData(cartList: List<Product>) {
        with(cartAdapter) {
            this.setCartList(cartList)
            this.notifyDataSetChanged()
        }
    }
}
