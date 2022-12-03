package com.example.storeapplication.cart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.cart.data.ProductsItem
import com.example.storeapplication.databinding.FragmentCartBinding
import com.example.storeapplication.home.ui.HomeViewModel
import com.example.storeapplication.utils.MySharedPreferences

class CartFragment : Fragment() {
    private lateinit var binding:FragmentCartBinding
    private val cartViewModel: CartViewModel by viewModels()
    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentCartBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val id = getIdFromShared()

        if (id != null) {
            cartViewModel.getUserCart(id= id)
            cartViewModel.cartItems.observe(viewLifecycleOwner,::getProductDetails)
        }
    }

    private fun getIdFromShared(): Int? {
        val obj = MySharedPreferences.getUserDataFromShared(requireContext())
        return obj.id
    }

    private fun getProductDetails(cartItems: List<ProductsItem?>?){
        val emptyList:ArrayList<GetProductResponseItem> = ArrayList()
        homeViewModel.getProducts()
        homeViewModel.itemList.observe(viewLifecycleOwner) { allProductsList ->
            cartItems?.let { cartItems ->
                for (cartItem in cartItems) {
                    val newCartItem = allProductsList?.find { it.id == cartItem?.productId }
                        ?.copy(quantity = cartItem?.quantity)
                    newCartItem?.let {
                        emptyList.add(it)
                    }
                }
                setDataOnRV(emptyList)
            }
        }
    }

    private fun setDataOnRV(cartList: MutableList<GetProductResponseItem>) {
        val cartAdapter = CartAdapter(cartList)
        binding.CartRV.adapter = cartAdapter
    }
}
