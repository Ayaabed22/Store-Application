package com.example.storeapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.databinding.ProductsItemUiBinding
import com.squareup.picasso.Picasso

class ProductsRVAdapter(private val productsList: MutableList<GetProductResponseItem>)
    :RecyclerView.Adapter<ProductsRVAdapter.ProductsViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ProductsItemUiBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        holder.binding.productName.text = productsList[position].title
        holder.binding.productPrice.text = "EGP: ${productsList[position].price}"

        Picasso.get().load(productsList[position].image).into(holder.binding.productImage)


    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductsViewHolder(val binding: ProductsItemUiBinding ):RecyclerView.ViewHolder(binding.root)
}
