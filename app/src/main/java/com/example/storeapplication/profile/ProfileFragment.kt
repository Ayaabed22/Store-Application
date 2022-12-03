package com.example.storeapplication.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.cart.data.GetAllUsersResponse
import com.example.storeapplication.databinding.FragmentProfileBinding
import com.example.storeapplication.utils.MySharedPreferences


class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel:ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val obj =MySharedPreferences.getUserDataFromShared(requireContext())
        getProfile(obj.id.toString())
        binding.userData = obj

        binding.cartFAB.setOnClickListener{
            view.findNavController().navigate(R.id.action_profileFragment_to_cartFragment)
        }
    }

    private fun getProfile(id: String) {
        profileViewModel.getUserData(id)
        profileViewModel.userProfileData.observe(viewLifecycleOwner) {
            bindData(it)
        }
    }

    private fun bindData(response: GetAllUsersResponse){
        binding.userData = response.copy()
    }

}