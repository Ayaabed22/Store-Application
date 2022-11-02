package com.example.storeapplication.profile

import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.databinding.FragmentProfileBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.P)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shared = requireActivity().getSharedPreferences("User Data", AppCompatActivity.MODE_PRIVATE)
        val userdata = shared.getString("userData", "")
        val gson = Gson()
        val json: String? = shared.getString("userData", "")
        val obj: GetAllUsersResponse? = gson.fromJson(json, GetAllUsersResponse::class.java)

        Log.i(TAG, "onViewCreated: $userdata")
        getProfile(obj?.id.toString())
        binding.userData = obj

        binding.cartFAB.setOnClickListener{
            view.findNavController().navigate(R.id.action_profileFragment_to_cartFragment)
        }
    }

    private fun getProfile(id: String) {

        RetrofitClient.getClient().getUserData(id).enqueue(object: Callback<GetAllUsersResponse> {
            override fun onResponse(
                call: Call<GetAllUsersResponse>,
                response: Response<GetAllUsersResponse>
            ) {
                Log.i(TAG, "onResponse: "+ response.body())
                bindData(response)
            }

            override fun onFailure(call: Call<GetAllUsersResponse>, t: Throwable) {
                Log.i(TAG, "onFailure: "+t.localizedMessage)
            }
        })
    }

    private fun bindData(response: Response<GetAllUsersResponse>){
        binding.userData = response.body()
    }

}