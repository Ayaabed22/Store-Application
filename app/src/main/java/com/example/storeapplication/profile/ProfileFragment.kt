package com.example.storeapplication.profile

import android.content.ContentValues.TAG
import android.os.Build
import android.os.Bundle
import android.service.autofill.UserData
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.databinding.FragmentProfileBinding
import com.example.storeapplication.signUp.*
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

        val shared = requireActivity().getSharedPreferences("User Data",
            AppCompatActivity.MODE_PRIVATE)

        val userdata = shared.getString("userData", "")

        val gson = Gson()
        val json: String? = shared.getString("userData", "")
        val obj: UserData = gson.fromJson(json, UserData::class.java)

        Log.i(TAG, "onViewCreated: $userdata")
            getProfile(2)
    }

    private fun getProfile(id: Int) {

        RetrofitClient.getClient().getUserData(id).enqueue(object: Callback<SignUpResponse> {
            override fun onResponse(
                call: Call<SignUpResponse>,
                response: Response<SignUpResponse>
            ) {
                Log.i(TAG, "onResponse: "+ response.body())
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Log.i(TAG, "onFailure: "+t.localizedMessage)
            }
        })
    }
}