package com.example.storeapplication.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentSplashBinding

class SplashFragment : Fragment() {
    private lateinit var binding: FragmentSplashBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentSplashBinding.inflate(inflater,container,false)
        Handler(Looper.myLooper()!!).postDelayed({
            MySharedPreferences.getPrefs(requireContext())
            val str = MySharedPreferences.getString(requireContext(),MySharedPreferences.KEY_MY_SHARED_String)
            val isLogin = MySharedPreferences.getBooleanLogin(requireContext(),MySharedPreferences.KEY_MY_SHARED_BOOLEAN_LOGIN)

            if (isLogin == true){
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
            }
            else{
                findNavController().navigate(R.id.action_splashFragment_to_signin)
            }
        },5000)
        return binding.root
    }
}