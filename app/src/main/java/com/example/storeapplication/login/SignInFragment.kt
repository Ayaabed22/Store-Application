package com.example.storeapplication.login

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.databinding.FragmentSigninBinding
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding


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
                call: Call<LoginResponse>,
                response: Response<LoginResponse>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: " + response.body().toString())
                    Log.i(TAG, "onResponse: "+ response.errorBody())
                    getUserID(userName)
                    view?.findNavController()?.navigate(R.id.action_signin_to_homeFragment)
                }
                else
                    Log.i(TAG, "onResponse: "+ response.errorBody())
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                Log.i(TAG, "onFailure:  " + t.localizedMessage)
            }

        })
    }

    fun getUserID(userName: String){
        RetrofitClient.getClient().getAllUsers().enqueue(object : Callback<MutableList<GetAllUsersResponse>>{
            override fun onResponse(
                call: Call<MutableList<GetAllUsersResponse>>,
                response: Response<MutableList<GetAllUsersResponse>>
            ) {
                if (response.isSuccessful){
                    Log.i(TAG, "onResponse: " + response.body().toString())
                    var userData = response.body()?.find { it.username == userName }
                    Log.i(TAG, "User Name: " + response.body()?.find { it.username == userName })
                    safeUserData(userData)

                }
            }

            override fun onFailure(call: Call<MutableList<GetAllUsersResponse>>, t: Throwable) {
                Log.i(TAG, "onFailure: "+ t.localizedMessage)
            }

        })
    }

    private fun safeUserData(userData: GetAllUsersResponse?) {
        val sharedPreference =  requireContext().getSharedPreferences("User Data", Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()
        val gson = Gson()
        val json = gson.toJson(userData)
        editor.putString("userData",json)
        editor.apply()
    }

    companion object {
        private const val  TAG = "SignIn"
    }
}