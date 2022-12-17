package com.example.storeapplication.favourite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.R
import com.example.storeapplication.databinding.ProductsItemUiBinding
import com.example.storeapplication.favourite.data.Favourite
import com.example.storeapplication.utils.Const
import com.squareup.picasso.Picasso

class FavouriteAdapter( private val clickListener: ClickListener)
    : RecyclerView.Adapter<FavouriteAdapter.FavouritesViewHolder>() {

    private var favouritesList: List<Favourite> = listOf()

    fun setFavouriteList(favouritesList: List<Favourite>){
        this.favouritesList = favouritesList
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        FavouritesViewHolder(ProductsItemUiBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val favouriteItem = favouritesList[holder.adapterPosition]

        with(holder.binding) {
            this.productName.text = favouriteItem.title
            this.productPrice.text = String.format(Const.PRICE_LABEL, favouriteItem.price)
            this.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)
            Picasso.get().load(favouriteItem.image).into(this.productImage)

            this.favouriteIcon.setOnClickListener {
                holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                clickListener.onFavouriteIconClick(Favourite(favouriteItem.id, favouriteItem.title, favouriteItem.price, favouriteItem.image))
            }
            holder.itemView.setOnClickListener { clickListener.onProductClick(favouriteItem.id) }
        }
    }

    override fun getItemCount(): Int { return favouritesList.size }

    class FavouritesViewHolder(val binding: ProductsItemUiBinding): RecyclerView.ViewHolder(binding.root)
}
