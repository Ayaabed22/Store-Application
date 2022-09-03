package com.example.storeapplication

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.databinding.FragmentHomeBinding
import com.google.gson.Gson
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private val TAG = "HomeFragment"
    lateinit var  binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.navImg.setOnClickListener {
            //Open Navigation Drawer
        }

        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productsRV.layoutManager = layoutManager



        RetrofitClient.getClient().getProducts().enqueue(object : Callback<MutableList<GetProductResponseItem>> {
            override fun onResponse(
                call: Call<MutableList<GetProductResponseItem>>,
                response: Response<MutableList<GetProductResponseItem>>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: "+ response.body())
                    val productsRVAdapter: ProductsRVAdapter =
                        ProductsRVAdapter(response.body() as MutableList<GetProductResponseItem>)
//                    productsRVAdapter.notifyDataSetChanged()
                    binding.productsRV.adapter = productsRVAdapter
                }
            }

            override fun onFailure(call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t.localizedMessage)
            }

        })
    }
}