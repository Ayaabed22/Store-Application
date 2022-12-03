package com.example.storeapplication.signUp.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentSignupBinding
import com.example.storeapplication.home.ui.HomeViewModel
import com.example.storeapplication.signIn.ui.SignInViewModel
import com.example.storeapplication.signUp.data.Address
import com.example.storeapplication.signUp.data.Name
import com.example.storeapplication.signUp.data.SignUpRequest

@Suppress("IMPLICIT_CAST_TO_ANY")
class SignupFragment : Fragment() {
    private lateinit var binding: FragmentSignupBinding
    private val signUpViewModel: SignUpViewModel by viewModels()
    private lateinit var view1: View

    private var fragmentContext: Context?=null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        fragmentContext = requireContext()
    }

    override fun onDetach() {
        super.onDetach()
        fragmentContext = null
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
        binding.signInBtn.setOnClickListener { view.findNavController().popBackStack() }

        binding.signUpBtn.setOnClickListener {
            signUpViewModel.checkEnteredData(getData())
        }
        signUpViewModel.isSuccessfulSignUp.observe(viewLifecycleOwner,::onSignUpResponse)
    }

    private fun getData() : SignUpRequest {
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

    private fun onSignUpResponse(isSuccessful: Boolean) = when (isSuccessful){
        true -> {
            makeToast("Register Success")
            view?.findNavController()?.popBackStack()
        }

        else ->makeToast("Register Failed")

    }

    private fun makeToast(text:String){
        activity?.runOnUiThread {
            Toast.makeText(fragmentContext,text,Toast.LENGTH_LONG).show()
        }
    }
}


