package com.example.storeapplication

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.databinding.FragmentHomeBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment(),ProductClick {

    private val TAG = "HomeFragment"
    lateinit var  binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topAppBar.setOnClickListener{
            openNavigationDrawer()
        }

        getProductsFromApI()

    }

    private fun getProductsFromApI() {
        RetrofitClient.getClient().getProducts().enqueue(object : Callback<MutableList<GetProductResponseItem>> {
            override fun onResponse(
                call: Call<MutableList<GetProductResponseItem>>,
                response: Response<MutableList<GetProductResponseItem>>
            ) {
                if (response.isSuccessful) {
                    showProductsOnRecyclerView(response)
                    Log.i(TAG, "onResponse: "+ response.body())
                }
            }

            override fun onFailure(call: Call<MutableList<GetProductResponseItem>>, t: Throwable) {
                Log.i(TAG, "onFailure: " + t.localizedMessage)
            }

        })
    }

    private fun showProductsOnRecyclerView(response: Response<MutableList<GetProductResponseItem>>) {
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.productsRV.layoutManager = layoutManager
        val productsRVAdapter = ProductsRVAdapter(response.body() as MutableList<GetProductResponseItem>,this)
        binding.productsRV.adapter = productsRVAdapter    }

    private fun openNavigationDrawer() {

        if (binding.drawableLayout.isDrawerOpen(GravityCompat.START)) {
            binding.drawableLayout.closeDrawer(GravityCompat.START)
        } else {
            binding.drawableLayout.openDrawer(GravityCompat.START)
        }
    }

    override fun itemClick(productId: Int) {
            Log.i(TAG, "itemClick: $productId")
            val action= HomeFragmentDirections.actionHomeFragmentToDeatilesFragment(productId)
            findNavController().navigate(action)
        }
    }



