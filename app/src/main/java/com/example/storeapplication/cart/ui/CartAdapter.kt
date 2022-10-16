package com.example.storeapplication.cart.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.GetProductResponseItem
import com.example.storeapplication.databinding.CartItemBinding
import com.squareup.picasso.Picasso

class CartAdapter(private val cartList: MutableList<GetProductResponseItem>)
    :RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        return CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        holder.binding.productTitle.text = cartList[position].title
        holder.binding.productPrice.text = "EGP: ${cartList[position].price}"
        holder.binding.count.text = cartList[position].quantity.toString()
        Picasso.get().load(cartList[position].image).into(holder.binding.productImage)
    }

    override fun getItemCount(): Int {
        return cartList.size
    }

    class CartViewHolder (val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root)
}