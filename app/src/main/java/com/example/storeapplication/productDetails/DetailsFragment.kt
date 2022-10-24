package com.example.storeapplication.productDetails

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.os.Bundle
import android.provider.Settings.Global.getInt
import android.provider.Settings.Secure.getInt
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.storeapplication.GetProductResponseItem
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.databinding.FragmentDeatilesBinding
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.reflect.Array.getInt


class DetailsFragment : Fragment(){
    private lateinit var binding: FragmentDeatilesBinding
    private lateinit var args : DetailsFragmentArgs
    private lateinit var productsList: MutableList<GetProductResponseItem>



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
        RetrofitClient.getClient().getProductDetails((productId+1).toString()).enqueue(object : Callback<GetProductResponseItem>{
            override fun onResponse(
                call: Call<GetProductResponseItem>,
                response: Response<GetProductResponseItem>
            ) {
                if (response.isSuccessful){
                    setData(response)
                    Log.i(TAG, "onResponse: " + response.body())
                }
            }

            override fun onFailure(call: Call<GetProductResponseItem>, t: Throwable) {
                Log.i(TAG, "onFailure: "+t.localizedMessage)
            }

        })
    }

    @SuppressLint("SetTextI18n")
    private fun setData(response:Response<GetProductResponseItem>) {
        binding.productName.text = response.body()?.title
        binding.productDescription.text = response.body()?.description
        binding.productPrice.text = "EGP: ${response.body()?.price}"
        Picasso.get().load(response.body()?.image).into(binding.productImage)
        binding.ratingbar.rating= response.body()?.rating?.rate?.toFloat()!!

    }

    }





