package com.example.storeapplication.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.databinding.SearchItemuiBinding
import com.example.storeapplication.favourite.ui.ClickListener
import com.example.storeapplication.utils.Const
import com.squareup.picasso.Picasso

class SearchViewAdapter(private var productsList: List<Product>, private var clickListener: ClickListener)
    : RecyclerView.Adapter<SearchViewAdapter.SearchViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        SearchViewHolder(SearchItemuiBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val productItem = productsList[holder.adapterPosition]

        with(holder.binding){
        this.productName.text = productItem.title
        this.productPrice.text = String.format(Const.PRICE_LABEL,productItem.price)
        this.ratingbar.rating = productItem.rating.rate.toFloat()

        Picasso.get().load(productItem.image).into(this.productImage)
        holder.itemView.setOnClickListener { clickListener.onProductClick(productItem.id) }
    }
    }

    override fun getItemCount(): Int { return productsList.size }

    class SearchViewHolder(val binding: SearchItemuiBinding):RecyclerView.ViewHolder(binding.root)
}





