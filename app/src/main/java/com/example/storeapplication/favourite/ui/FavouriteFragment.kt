package com.example.storeapplication.favourite.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.HomeFragmentDirections
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentFavouriteBinding
import com.example.storeapplication.favourite.data.FavouriteDatabase
import com.example.storeapplication.utils.Const.Companion.favouriteDao

class FavouriteFragment : Fragment() ,ItemClick{
    private lateinit var binding:FragmentFavouriteBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(layoutInflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        favouriteDao = FavouriteDatabase.getDatabaseInstance(requireContext()).favouriteDao()

        favouriteDao.getFavourites()
        setDataOnRV()
    }


    private fun setDataOnRV(){
        val layoutManager = GridLayoutManager(requireContext(), 2)
        binding.favouriteRV.layoutManager = layoutManager
        val favouriteAdapter = FavouriteAdapter(favouriteDao.getFavourites(),this)
        binding.favouriteRV.adapter = favouriteAdapter
    }

    override fun favouriteClickListener(id: Int, name: String, price: Double, image: String) {
        favouriteDao.deleteByItemId(id)
        setDataOnRV()
    }

    override fun productClickListener(id: Int) {
        val action= FavouriteFragmentDirections.actionFavouriteFragmentToDeatilesFragment(id)
        findNavController().navigate(action)
    }
}