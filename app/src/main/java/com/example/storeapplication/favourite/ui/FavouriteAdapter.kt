package com.example.storeapplication.favourite.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.R
import com.example.storeapplication.databinding.ProductsItemUiBinding
import com.example.storeapplication.favourite.data.FavouriteModel
import com.squareup.picasso.Picasso

class FavouriteAdapter(private val favouritesList: List<FavouriteModel>, private val itemClick: ItemClick)
    : RecyclerView.Adapter<FavouriteAdapter.FavouritesViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(ProductsItemUiBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        holder.binding.productName.text = favouritesList[position].title
        holder.binding.productPrice.text = favouritesList[position].price.toString()
        Picasso.get().load(favouritesList[position].image).into(holder.binding.productImage)
        holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)

        holder.binding.favouriteIcon.setOnClickListener {
            holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            itemClick.itemClickListener( favouritesList[position].id,favouritesList[position].title,favouritesList[position].price,favouritesList[position].image)
        }
    }

    override fun getItemCount(): Int {
        return favouritesList.size
    }

    class FavouritesViewHolder(val binding: ProductsItemUiBinding): RecyclerView.ViewHolder(binding.root)
}