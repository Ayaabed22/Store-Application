package com.example.storeapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import com.example.storeapplication.databinding.FragmentSignupBinding
import com.example.storeapplication.signUp.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val TAG = "SignupFragment"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentSignupBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.signInBtn.setOnClickListener {
            view.findNavController().popBackStack()
        }


        binding.signUpBtn.setOnClickListener {
            val signUpRequest = SignUpRequest("John@gmail.com","johnd","m38rmF$",
                Name("John","Doe"),
                Address(
                    Geolocation("-37.3159","81.1496"),
                    "kilcoole", "7835 new road",3,"12926-3874"),"1-570-236-7033")

            RetrofitClient.getClient().signUp(signUpRequest).enqueue(object: Callback<SignUpResponse>{
                override fun onResponse(
                    call: Call<SignUpResponse>,
                    response: Response<SignUpResponse>
                ) {
                    if (response.isSuccessful){
                        Log.i(TAG, "onResponse: "+response.body().toString())
                    }
                }

                override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                    Log.i(TAG, "onFailure: "+t.localizedMessage)
                }

            })
        }
    }
}