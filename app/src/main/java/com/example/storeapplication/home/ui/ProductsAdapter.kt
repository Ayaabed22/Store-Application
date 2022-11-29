package com.example.storeapplication.home.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.R
import com.example.storeapplication.databinding.ProductsItemUiBinding
import com.example.storeapplication.favourite.ui.ItemClick
import com.squareup.picasso.Picasso

class ProductsAdapter(private val productsList: MutableList<GetProductResponseItem>, private val itemClick: ItemClick)
    :RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ProductsItemUiBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val id:Int = productsList[holder.adapterPosition].id
        val title:String = productsList[holder.adapterPosition].title
        val price:Double = productsList[holder.adapterPosition].price
        val image:String = productsList[holder.adapterPosition].image

        holder.binding.productName.text = title
        holder.binding.productPrice.text = "EGP: $price"
        Picasso.get().load(image).into(holder.binding.productImage)

        holder.binding.favouriteIcon.setOnClickListener {
            holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)
            itemClick.favouriteClickListener(id,title,price,image)
        }

        holder.itemView.setOnClickListener {itemClick.productClickListener(id)
        }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductsViewHolder(val binding: ProductsItemUiBinding):RecyclerView.ViewHolder(binding.root)
}
