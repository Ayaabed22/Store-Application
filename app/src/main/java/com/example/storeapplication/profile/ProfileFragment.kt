package com.example.storeapplication.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentProfileBinding
import com.example.storeapplication.utils.MySharedPreferences

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val profileViewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userData = MySharedPreferences.getUserDataFromShared(requireContext())

        getProfile(userData.id.toString())
        binding.userData = userData
        binding.cartFAB.setOnClickListener {
            view.findNavController().navigate(R.id.action_profileFragment_to_cartFragment)
        }
    }

    private fun getProfile(id: String) {
        profileViewModel.getUserData(id)
        profileViewModel.userProfileData.observe(viewLifecycleOwner) { binding.userData = it.copy() }
    }
}