package com.example.storeapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.databinding.ProductsItemUiBinding
import com.example.storeapplication.favourite.ui.ItemClick
import com.squareup.picasso.Picasso

class ProductsRVAdapter(private val productsList: MutableList<GetProductResponseItem>,private val itemClick: ItemClick)
    :RecyclerView.Adapter<ProductsRVAdapter.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ProductsItemUiBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        val name:String = productsList[holder.adapterPosition].title
        val price:Double = productsList[holder.adapterPosition].price
        val image:String = productsList[holder.adapterPosition].image
        val id = productsList[holder.adapterPosition].id
        holder.binding.productName.text = name
        holder.binding.productPrice.text = "EGP: $price"
        Picasso.get().load(image).into(holder.binding.productImage)

        holder.binding.favouriteIcon.setOnClickListener {
            holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)
            itemClick.itemClickListener(id,name,price,image)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductsViewHolder(val binding: ProductsItemUiBinding ):RecyclerView.ViewHolder(binding.root)
}
