package com.example.storeapplication.productDetails

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.storeapplication.home.data.Product
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentDeatilesBinding
import com.example.storeapplication.favourite.data.Favourite
import com.example.storeapplication.favourite.ui.FavouriteViewModel
import com.example.storeapplication.utils.Const
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment(){
    private lateinit var binding: FragmentDeatilesBinding
    private lateinit var args : DetailsFragmentArgs
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()
    private val favouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeatilesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        args = DetailsFragmentArgs.fromBundle(requireArguments())
        Log.i(TAG, "onViewCreated: " + args.detailsArgs)
        getProductDetails(args.detailsArgs)
}
    private fun getProductDetails(productId: Int)
    {
        productDetailsViewModel.productDetails(productId = productId)
        productDetailsViewModel.itemDetails.observe(viewLifecycleOwner,::setDataOnUI)
    }

    private fun setDataOnUI(response:Product) {
        binding.apply {
            productName.text = response.title
            productDescription.text = response.description
            productPrice.text = String.format(Const.PRICE_LABEL,response.price)
            Picasso.get().load(response.image).into(productImage)
            ratingbar.rating= response.rating.rate.toFloat()

            favouriteIcon.setOnClickListener { addToFavourite(response) }
        }
    }

    private fun addToFavourite(body: Product) {
        binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)
        val favourite = Favourite(body.id,body.title,body.price,body.image)
//        favouriteViewModel.insertFavourite(favourite)
    }
}





