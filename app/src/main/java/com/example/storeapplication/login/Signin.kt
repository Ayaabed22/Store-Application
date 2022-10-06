package com.example.storeapplication.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.databinding.FragmentSigninBinding
import retrofit2.Callback
import retrofit2.Response

class Signin: Fragment() {

    private lateinit var binding: FragmentSigninBinding
    private val TAG = "Signin"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSigninBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signUpBtn.setOnClickListener {
            view.findNavController().navigate(R.id.action_signin_to_signupFragment)
        }

        binding.signInBtn.setOnClickListener {
            val userName = binding.etUserName.text.toString()
            val password = binding.etPassword.text.toString()

            checkEnteredData(userName,password)
        }

    }

    private fun checkEnteredData(userName:String , password:String) {
        RetrofitClient.getClient().login(LoginRequest(userName,password)).enqueue(object: Callback<LoginResponse> {
            override fun onResponse(
                call: retrofit2.Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: " + response.body().toString())

                    val shared = requireActivity().getSharedPreferences("token",
                        AppCompatActivity.MODE_PRIVATE)

                    val tokenEditor = shared.edit()
                    tokenEditor.putString("token", response.body().toString())

                    tokenEditor.apply()

                    view?.findNavController()?.navigate(R.id.action_signin_to_homeFragment)
                }
                else
                    Log.i(TAG, "onResponse: "+ response.errorBody())
            }

            override fun onFailure(call: retrofit2.Call<LoginResponse>, t: Throwable) {
                Log.i(TAG, "onFailure:  " + t.localizedMessage)
            }

        })
    }
}