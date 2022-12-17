package com.example.storeapplication.cart.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.databinding.CartItemBinding
import com.example.storeapplication.utils.Const
import com.squareup.picasso.Picasso

class CartAdapter : RecyclerView.Adapter<CartAdapter.CartViewHolder>() {

    private var cartList: List<Product> = ArrayList()

    fun setCartList(cartList: List<Product>){ this.cartList = cartList }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        val cartItem = cartList[position]
        with(holder.binding) {
            this.productTitle.text = cartItem.title
            this.productPrice.text = String.format(Const.PRICE_LABEL, cartItem.price)
            this.count.text = cartList[position].quantity.toString()

            Picasso.get().load(cartItem.image).into(this.productImage)
        }
    }

    override fun getItemCount(): Int { return cartList.size }

    class CartViewHolder(val binding: CartItemBinding) : RecyclerView.ViewHolder(binding.root)
}