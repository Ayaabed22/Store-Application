package com.example.storeapplication.productDetails

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.storeapplication.home.data.GetProductResponseItem
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentDeatilesBinding
import com.example.storeapplication.favourite.data.FavouriteDatabase
import com.example.storeapplication.favourite.data.FavouriteModel
import com.example.storeapplication.utils.Const.Companion.favouriteDao
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment(){
    private lateinit var binding: FragmentDeatilesBinding
    private lateinit var args : DetailsFragmentArgs
    private val productDetailsViewModel: ProductDetailsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDeatilesBinding.inflate(inflater, container, false)
        return binding.root
/*TODO: remove extra empty line*/
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

/*TODO: remove extra empty lines*/
        args = DetailsFragmentArgs.fromBundle(requireArguments())
        Log.i(TAG, "onViewCreated: " + args.detailsArgs)
        getProductDetails(args.detailsArgs)

}
    private fun getProductDetails(productId: Int)
    {
        productDetailsViewModel.productDetails(productId = productId)
        productDetailsViewModel.itemDetails.observe(viewLifecycleOwner) {
            setData(it)
        }
    }

    @SuppressLint("SetTextI18n")/*TODO: remove & fix warning*/
    private fun setData(response:GetProductResponseItem) {  /*TODO: try to give a more meaningful name*/
        /*TODO: make use of binding.apply{} */

        binding.productName.text = response.title
        binding.productDescription.text = response.description
        binding.productPrice.text = "EGP: ${response.price}"
        Picasso.get().load(response.image).into(binding.productImage)
        binding.ratingbar.rating= response.rating.rate.toFloat()

        binding.favouriteIcon.setOnClickListener {
            addToFavourite(response)
        } /*TODO: could be shortened in one line*/
    }

    private fun addToFavourite(body: GetProductResponseItem) {
        binding.favouriteIcon.setImageResource(R.drawable.ic_baseline_favorite_24)
        favouriteDao = FavouriteDatabase.getDatabaseInstance(requireContext()).favouriteDao() /*TODO: should happen once in VM*/
        favouriteDao.insertItem(FavouriteModel(body.id,body.title,body.price,body.image)) /*TODO: should hbe moved to VM*/
        /*TODO: this conversion [FavouriteModel(body.id,body.title,body.price,body.image)] should have a separate method*/
    }

}





