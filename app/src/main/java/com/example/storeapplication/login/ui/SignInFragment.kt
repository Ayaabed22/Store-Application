package com.example.storeapplication.login.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentSigninBinding

class SignInFragment : Fragment() {

    private lateinit var binding: FragmentSigninBinding

    //private val viewModel = ViewModelProvider(this).get(SignInViewModel::class.java)
    private val viewModel: SignInViewModel by viewModels()

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
            viewModel.checkEnteredData(
                userName = binding.etUserName.text.toString(),
                password = binding.etPassword.text.toString()
            )
        }
        viewModel.isSuccessfulAuthentication.observe(viewLifecycleOwner, ::onSignInResponse)
    }

   /* private fun onSignInResponse(isSuccessful: Boolean) = if (isSuccessful) {
        navigate(R.id.action_signin_to_homeFragment)
    } else {
        Toast.makeText(requireContext(), "Sign In Failed!", Toast.LENGTH_SHORT).show()
    }*/

   private fun onSignInResponse(isSuccessful: Boolean) = when (isSuccessful) {
       true -> navigate(R.id.action_signin_to_homeFragment)
       else -> Toast.makeText(requireContext(), "Sign In Failed!", Toast.LENGTH_SHORT).show()
   }

   private fun navigate(destination: Int) = view?.findNavController()?.navigate(destination)
}