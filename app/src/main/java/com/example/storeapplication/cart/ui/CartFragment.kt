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

        val id = getIdFromShared() /*TODO: try to avoid ambiguous naming like this, for example just by looking I don't know what id is this*/

        if (id != null) {
            cartViewModel.getUserCart(id= id) /*TODO: change 'id' ambiguous naming*/
            cartViewModel.cartItems.observe(viewLifecycleOwner,::getProductDetails)
        }

    }

    private fun getIdFromShared(): Int? {/*TODO: change 'shared' ambiguous naming*/
        val obj = MySharedPreferences.getUserDataFromShared(requireContext())/*TODO: change 'obj' ambiguous naming*/
        return obj.id
    }/*TODO: Also this could be changed to a one liner function*/

    private fun getProductDetails(cartItems: List<ProductsItem?>?){
        val emptyList:ArrayList<GetProductResponseItem> = ArrayList() /*TODO:  change 'emptyList' ambiguous naming*/
        homeViewModel.getProducts()
        homeViewModel.itemList.observe(viewLifecycleOwner) { allProductsList ->
            cartItems?.let { cartItems -> /*TODO: there's a lot of nesting here, try to reduce it, maybe use:  cartItems?.forEach {  }
                  cartItems?.forEach { cartItem ->
                val newCartItem = allProductsList?.find { it.id == cartItem?.productId }?.copy(quantity = cartItem?.quantity)
                newCartItem?.let { emptyList.add(it) }
            }
            setDataOnRV(emptyList)
            */
                for (cartItem in cartItems) {
                    val newCartItem = allProductsList?.find { it.id == cartItem?.productId }
                        ?.copy(quantity = cartItem?.quantity)
                    newCartItem?.let {
                        emptyList.add(it)
                    }
                }

                setDataOnRV(emptyList) /*TODO: Are we sure want to update this list on every iteration?/please check other fragments with the same function for the same comment*/
            }
        }
    }

    /*TODO: no need for a mutable list, make it a normal list*/
    private fun setDataOnRV(cartList: MutableList<GetProductResponseItem>) { /*TODO:  change 'setDataOnRV' ambiguous naming*/
        val cartAdapter = CartAdapter(cartList) /*TODO: why are we creating a new adapter everytime? better extract this variable outside and make it a lateinit one*/
        binding.CartRV.adapter = cartAdapter
    }/*TODO:please check other fragments with the same function for the same comments*/
 /*
    private fun setCartRVData(cartList: MutableList<GetProductResponseItem>) {
        cartAdapter.updateList(cartList)
        cartAdapter.notifyDataSetChanged()
    }*/
}
