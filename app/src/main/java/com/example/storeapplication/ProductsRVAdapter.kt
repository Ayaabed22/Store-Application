package com.example.storeapplication

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class ProductsRVAdapter( private var productsList: MutableList<GetProductResponseItem>,
                         private val ProductClick:productClick)

    :RecyclerView.Adapter<ProductsRVAdapter.ProductsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.products_item_ui, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.productTitle.text = productsList[position].title
       // holder.productdescription.text = productsList[position].description
        holder.productPrice.text = productsList[position].price.toString()

        holder.productImage.setImageResource(
            Picasso.get().load(productsList[position].image).into(holder.productImage)
        )

        holder.itemView.setOnClickListener { ProductClick.itemClick(productid = productsList[position].id) }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var productImage: ImageView
        var productTitle: TextView
        var productPrice: TextView
       // var ratingBar: RatingBar
       // var addbutton: Button
       // var productdescription: TextView


        init {
           // productdescription = itemView.findViewById(R.id.details_description)
           // ratingBar = itemView.findViewById(R.id.tv_rating)
          //  addbutton = itemView.findViewById(R.id.button)
            productImage = itemView.findViewById(R.id.product_image)
            productTitle = itemView.findViewById(R.id.product_name)
            productPrice = itemView.findViewById(R.id.product_price)


        }




    }
}

private fun ImageView.setImageResource(into: Unit) {

}
