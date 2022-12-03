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

/*TODO: 2 empty lines aren't necessary, 1 is enough. Check the other files for the same please*/
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouritesViewHolder {
        return FavouritesViewHolder(ProductsItemUiBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    @SuppressLint("SetTextI18n") /*TODO: supress a warning doesn't fix it, please remove this line and fix it, fix in other adapters*/
    override fun onBindViewHolder(holder: FavouritesViewHolder, position: Int) {
        /*TODO: in the following 3 variables, it's redundant to add the data type so please remove them*/
        val name:String = favouritesList[holder.adapterPosition].title /*TODO: favouritesList[holder.adapterPosition] could be extracted in a separate variable to avoid duplication*/
        val price:Double = favouritesList[holder.adapterPosition].price
        val image:String = favouritesList[holder.adapterPosition].image
        val id = favouritesList[holder.adapterPosition].id /*TODO: after applying above comments this variable should be deleted*/
        /*TODO: you need to have spacing here to make reading the code easier*/
        holder.binding.productName.text = name
        holder.binding.productPrice.text = "EGP: $price"
        Picasso.get().load(image).into(holder.binding.productImage)
        holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)
        holder.itemView.setOnClickListener { itemClick.productClickListener(id) }
        holder.binding.favouriteIcon.setOnClickListener {
            holder.binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_border_24)
            itemClick.favouriteClickListener(id,name,price,image)
        }
    }

    override fun getItemCount(): Int {
        return favouritesList.size
    }

    class FavouritesViewHolder(val binding: ProductsItemUiBinding): RecyclerView.ViewHolder(binding.root)
}