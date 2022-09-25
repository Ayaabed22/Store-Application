package com.example.storeapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.productDetails.ProductClick
import com.squareup.picasso.Picasso

class ProductsRVAdapter(private var productsList: MutableList<GetProductResponseItem>,var Productclick: ProductClick
) :RecyclerView.Adapter<ProductsRVAdapter.ProductsViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.products_item_ui,parent,false))
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        holder.productTitle.text = productsList[position].title
        holder.productPrice.text = productsList[position].price.toString()

     Picasso.get().load(productsList[position].image).into(holder.productImage)

        holder.itemView.setOnClickListener{Productclick.itemClick(holder.adapterPosition) }

    }

    override fun getItemCount(): Int {
        return productsList.size
    }


    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var productImage: ImageView
        var productTitle: TextView
        var productPrice: TextView

        init{
            productImage = itemView.findViewById(R.id.product_image)
            productTitle = itemView.findViewById(R.id.product_name)
            productPrice = itemView.findViewById(R.id.product_price)
        }
    }


}

