package com.example.storeapplication
import android.content.ContentValues.TAG
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.storeapplication.databinding.ProductsItemUiBinding
import com.squareup.picasso.Picasso

class ProductsRVAdapter(private var productsList: MutableList<GetProductResponseItem>,
                        private val ProductClick: ProductClick
)

    :RecyclerView.Adapter<ProductsRVAdapter.ProductsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        return ProductsViewHolder(ProductsItemUiBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }


    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.binding.productName.text = productsList[position].title
        holder.binding.productPrice.text = "EGP: ${productsList[position].price}"

        Picasso.get().load(productsList[position].image).into(holder.binding.productImage)


     holder.itemView.setOnClickListener {
         Log.i("TAG", "onBindViewHolder: " + holder.adapterPosition)
         ProductClick.itemClick(holder.adapterPosition)
     }
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    class ProductsViewHolder(val binding: ProductsItemUiBinding ):RecyclerView.ViewHolder(binding.root)

}

