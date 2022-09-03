package com.example.storeapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.databinding.ProductsItemUiBinding
import com.squareup.picasso.Picasso

class ProductsRVAdapter(private val productsList: MutableList<GetProductResponseItem>)
    :RecyclerView.Adapter<ProductsRVAdapter.ProductsViewHolder>(){



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.products_item_ui,parent,false))
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {

        holder.productTitle.text = productsList[position].title
        holder.productPrice.text = productsList[position].price.toString()

        holder.productImage.setImageResource(Picasso.get().load(productsList[position].image).into(holder.productImage))
        

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

private fun ImageView.setImageResource(into: Unit) {

}
