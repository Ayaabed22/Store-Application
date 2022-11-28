package com.example.storeapplication.search

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.R
import com.example.storeapplication.favourite.ui.ItemClick
import com.squareup.picasso.Picasso

class SearchViewAdapter(
    private var productsList: MutableList<GetProductResponseItem>,
    private var itemClick: ItemClick
) : RecyclerView.Adapter<SearchViewAdapter.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_itemui, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val name:String = productsList[holder.adapterPosition].title
        val price:Double = productsList[holder.adapterPosition].price
        val image:String = productsList[holder.adapterPosition].image
        val id = productsList[holder.adapterPosition].id

        holder.productTitle.text = name
        holder.productPrice.text = "EGP: $price"
        holder.ratingBar.rating = productsList[position].rating.rate.toFloat()

        Picasso.get().load(image).into(holder.productImage)
        holder.itemView.setOnClickListener { itemClick.productClickListener(id) }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }


    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage: ImageView
        var productTitle: TextView
        var productPrice: TextView
        var ratingBar: RatingBar

        init {
            productImage = itemView.findViewById(R.id.product_image)
            productTitle = itemView.findViewById(R.id.product_name)
            productPrice = itemView.findViewById(R.id.product_price)
            ratingBar = itemView.findViewById(R.id.ratingbar)
        }
    }
}





