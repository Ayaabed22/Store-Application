package com.example.storeapplication.signUp.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.storeapplication.RetrofitClient
import com.example.storeapplication.databinding.FragmentSignupBinding
import com.example.storeapplication.signUp.*
import com.example.storeapplication.signUp.data.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    companion object {
        private const val TAG = "SignupFragment"
    }

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
            val signUpRequest = getData()

            RetrofitClient.getClient().signUp(signUpRequest)
                .enqueue(object : Callback<SignUpResponse> {
                    override fun onResponse(
                        call: Call<SignUpResponse>,
                        response: Response<SignUpResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.i(TAG, "onResponse: " + response.body().toString())
                            Toast.makeText(requireContext(),"Register Success",Toast.LENGTH_LONG).show()
                        }
                        else{
                            Log.i(TAG, "onResponse: " + response.errorBody().toString())
                            Toast.makeText(requireContext(),response.errorBody().toString(),Toast.LENGTH_LONG).show()
                        }
                    }
                    override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                        Log.i(TAG, "onFailure: " + t.localizedMessage)
                        Toast.makeText(requireContext(),t.localizedMessage,Toast.LENGTH_LONG).show()
                    }
                })

            view.findNavController().popBackStack()
        }
    }

    private fun getData() : SignUpRequest{
        val firstName = binding.etFirstName.text.toString()
        val lastName = binding.etLastName.text.toString()
        val userName = binding.etUserName.text.toString()
        val email = binding.etEmail.text.toString()
        val password = binding.etPassword.text.toString()
        val phone = binding.etPhone.text.toString()
        val city = binding.etCity.text.toString()
        val street = binding.etStreet.text.toString()
        val number = binding.etNumber.text.toString()

        return SignUpRequest(email, userName, password,
            Name(firstName, lastName),
            Address(city, street, number),phone)
    }
}