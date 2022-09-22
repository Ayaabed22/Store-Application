package com.example.storeapplication

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.storeapplication.databinding.FragmentDeatilesBinding
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DetailsFragment : Fragment(){
    lateinit var binding: FragmentDeatilesBinding
    private lateinit var args :DetailsFragmentArgs

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


    private fun getProductDetails(productId: Int) {
        RetrofitClient.getClient().getProducts().enqueue(object : Callback<MutableList<GetProductResponseItem>>{
            override fun onResponse(
                call: Call<MutableList<GetProductResponseItem>>,
                response: Response<MutableList<GetProductResponseItem>>
            ) {
                if (response.isSuccessful){
                    Log.i(TAG, "onResponse: "+ response.body())
                    val myProduct = response.body()?.find { it.productId == (productId+1) }

                    Log.i(TAG, "onResponse: $myProduct")

                    if (myProduct != null) {
                        setData(myProduct)
                    }
                }
            }

            override fun onFailure(call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                Log.i(TAG, "onFailure: " +t.localizedMessage)
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun setData(product:GetProductResponseItem) {
        binding.productName.text = product.title
        binding.productDescription.text = product.description
        binding.productPrice.text = "EGP: ${product.price}"
        Picasso.get().load(product.image).into(binding.productImage)
    }

    }





