package com.example.storeapplication

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.storeapplication.databinding.FragmentProfileBinding
import com.example.storeapplication.databinding.FragmentSigninBinding
import com.example.storeapplication.databinding.FragmentSignupBinding
import com.example.storeapplication.login.LoginRequest
import com.example.storeapplication.login.LoginResponse
import com.example.storeapplication.signUp.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val shared = requireActivity().getSharedPreferences("token",
            AppCompatActivity.MODE_PRIVATE)

        val token = shared.getString("token", null)

        if (token != null) {
            getProfile(token)
        }
    }

    private fun getProfile(auth: String) {

        RetrofitClient.getClient().getProfile(auth).enqueue(object: Callback<SignUpRequest> {

            override fun onResponse(call: Call<SignUpRequest>, response: Response<SignUpRequest>) {
                if (response.isSuccessful) {

                    Log.i(TAG, "onResponse: " + response.body().toString())

                }
                else
                    Log.i(TAG, "onResponse: "+ response.errorBody())
            }

            override fun onFailure(call: Call<SignUpRequest>, t: Throwable) {
                Log.i(TAG, "onFailure:  " + t.localizedMessage)
            }

            })
    }
}