package com.example.storeapplication.cart.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.databinding.CartItemBinding
import com.squareup.picasso.Picasso

class CartAdapter(private val cartList: MutableList<GetProductResponseItem>) /*TODO: there's no need to share a mutable list to the adapter because you won't change anything in it*/
    :RecyclerView.Adapter<CartAdapter.CartViewHolder>(){

    /*TODO: This is a one line function, could be shortened into:
       override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
         CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
         [IMPLEMENT IN OTHER FILES AS WELL]
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        CartViewHolder(CartItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        /* TODO: We could reduce the duplication of (holder.binding) with something like this:
            with(holder.binding){
                this.productTitle.text = .......
                this.productPrice.text = .......
                ........
                .......
            }
        */
        /*TODO: extract cartList[position] into a variable
        *  val cartItem = cartList[position]*/
        holder.binding.productTitle.text = cartList[position].title
        /*TODO: this could be extracted to a string & formatted
        *  1- In strings.xml: <string name="price_label">EGP: %s</string>
        *  2- here: holder.binding.productPrice.text = String.format(R.string.price_label, cartList[position].price)
        *  3- please check if there's any other hardcoded text and fix it the same way
        *  4- Remove @SuppressLint("SetTextI18n")
        * */
        holder.binding.productPrice.text = "EGP: ${cartList[position].price}"
        holder.binding.count.text = cartList[position].quantity.toString()
        Picasso.get().load(cartList[position].image).into(holder.binding.productImage)
    }

    /*TODO: This is a one line function, could be shortened into:
        override fun getItemCount() = cartList.size
    *  */
    override fun getItemCount(): Int {
        return cartList.size
    }

    class CartViewHolder (val binding: CartItemBinding):RecyclerView.ViewHolder(binding.root)
}

/*TODO: Please implement same comments on other Adapters as well*/