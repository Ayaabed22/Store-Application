package com.example.storeapplication.home.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.R
import com.example.storeapplication.databinding.ProductsItemUiBinding
import com.example.storeapplication.favourite.data.Favourite
import com.example.storeapplication.favourite.ui.ClickListener
import com.example.storeapplication.utils.Const
import com.squareup.picasso.Picasso

class ProductsAdapter( private val clickListener: ClickListener) :RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>(){

    private var productList: List<Product> = ArrayList()
    fun setProductList(productList: List<Product>){ this.productList = productList }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ProductsViewHolder(ProductsItemUiBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val productItem = productList[holder.adapterPosition]
        val isFavouriteState = false

        with(holder.binding) {
            this.productName.text = productItem.title
            //context.getString(R.string.Price_label)
            this.productPrice.text = String.format(Const.PRICE_LABEL, productItem.price)
            Picasso.get().load(productItem.image).into(this.productImage)

            this.favouriteIcon.setOnClickListener {
                isFavouriteState != isFavouriteState
                clickListener.onFavouriteIconClick(Favourite(productItem.id,productItem.title,productItem.price,productItem.image))
                if (isFavouriteState){
                    this.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)
                }
                else
                    this.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            }
        }
        holder.itemView.setOnClickListener {clickListener.onProductClick(productItem.id) }
    }

    override fun getItemCount(): Int { return productList.size }

    class ProductsViewHolder(val binding: ProductsItemUiBinding):RecyclerView.ViewHolder(binding.root)
}