package com.example.storeapplication

import android.annotation.SuppressLint
import android.content.ClipData
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.productDetails.ProductClick
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList

class AdaterSearchview(
    private var productsList: MutableList<GetProductResponseItem>,
    private var ProductClick:ProductClick
) : RecyclerView.Adapter<AdaterSearchview.ProductsViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.search_itemui, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        holder.productTitle.text = productsList[position].title
        holder.productPrice.text = productsList[position].price.toString()
        holder.ratingBar.rating = productsList[position].rating.rate.toFloat()

        Picasso.get().load(productsList[position].image).into(holder.productImage)
        holder.itemView.setOnClickListener { ProductClick.itemClick(holder.adapterPosition) }


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





