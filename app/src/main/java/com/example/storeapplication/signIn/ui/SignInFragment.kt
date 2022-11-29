package com.example.storeapplication.signIn.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.databinding.FragmentSigninBinding
import com.example.storeapplication.utils.MySharedPreferences
import com.example.storeapplication.utils.MySharedPreferences.KEY_MY_SHARED_String
import com.example.storeapplication.utils.MySharedPreferences.KEY_MY_SHARED_BOOLEAN_LOGIN
import com.google.gson.Gson

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding
    private val signInViewModel:SignInViewModel by viewModels()

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

        binding.signUpBtn.setOnClickListener { navigate(R.id.action_signin_to_signupFragment) }
        binding.signInBtn.setOnClickListener {
            signInViewModel.checkEnteredData(
                userName = binding.etUserName.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
        signInViewModel.isSuccessfulAuthentication.observe(viewLifecycleOwner, ::onSignInResponse)
    }

    private fun onSignInResponse(isSuccessful: Boolean) = when (isSuccessful) {
        true -> {
            getUserData(userName = binding.etUserName.text.toString())
            navigate(R.id.action_signin_to_homeFragment)
        }
        else -> Toast.makeText(requireContext(), "Sign In Failed!", Toast.LENGTH_SHORT).show()
    }

    private fun navigate(destination: Int) = view?.findNavController()?.navigate(destination)

    private fun getUserData(userName: String){
        signInViewModel.getUserData(userName)
        signInViewModel.userData.observe(viewLifecycleOwner,::safeUserData)
    }

    private fun safeUserData(userData: GetAllUsersResponse?) {
        val gson = Gson()
        val json = gson.toJson(userData)
        MySharedPreferences.getPrefs(requireContext())
        MySharedPreferences.saveBoolean(requireContext(),KEY_MY_SHARED_BOOLEAN_LOGIN,true)
        MySharedPreferences.saveString(requireContext(),KEY_MY_SHARED_String,json)
    }
}