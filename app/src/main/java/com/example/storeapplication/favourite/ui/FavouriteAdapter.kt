package com.example.storeapplication.favourite.ui

import android.annotation.SuppressLint
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

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        val name:String = favouritesList[holder.adapterPosition].title
        val price:Double = favouritesList[holder.adapterPosition].price
        val image:String = favouritesList[holder.adapterPosition].image
        val id = favouritesList[holder.adapterPosition].id
        holder.binding.productName.text = name
        holder.binding.productPrice.text = "EGP: $price"
        Picasso.get().load(image).into(holder.binding.productImage)
        holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)

        holder.binding.favouriteIcon.setOnClickListener {
            holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            itemClick.favouriteClickListener(id,name,price,image)
        }

        holder.itemView.setOnClickListener {itemClick.productClickListener(id) }
    }

    override fun getItemCount(): Int {
        return favouritesList.size
    }

    class FavouritesViewHolder(val binding: ProductsItemUiBinding): RecyclerView.ViewHolder(binding.root)
}