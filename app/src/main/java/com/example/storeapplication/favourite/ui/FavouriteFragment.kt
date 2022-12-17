package com.example.storeapplication.favourite.ui

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.R
import com.example.storeapplication.databinding.FragmentFavouriteBinding
import com.example.storeapplication.favourite.data.Favourite
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class FavouriteFragment : Fragment(), ClickListener {

    private lateinit var binding: FragmentFavouriteBinding
    private val favouriteAdapter = FavouriteAdapter( this)
    private val favouriteViewModel: FavouriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavouriteBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = GridLayoutManager(requireContext(), 2)

        binding.favouriteRV.layoutManager = layoutManager
        binding.favouriteRV.adapter = favouriteAdapter

        setListToAdapter()
        favouriteViewModel.getFavourites()
    }

    private fun setListToAdapter() {
        favouriteViewModel.favouriteList.observe(viewLifecycleOwner) {favouriteList->
            favouriteAdapter.setFavouriteList(favouriteList)
        }
    }

    override fun onFavouriteIconClick(favourite: Favourite) {
        showAlertDialog(favourite)
    }

    override fun onProductClick(productID: Int) {
        findNavController().navigate(FavouriteFragmentDirections.actionFavouriteFragmentToDeatilesFragment(productID))
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showAlertDialog(favourite: Favourite) {
        MaterialAlertDialogBuilder(requireContext())
            .setMessage(resources.getString(R.string.dialogMassage))
            .setNegativeButton(resources.getString(R.string.decline)) { dialog, _ ->
                dialog.cancel()
            }
            .setPositiveButton(resources.getString(R.string.accept)) { _, _ ->
                favouriteViewModel.updateFavouriteList(favourite)
                favouriteAdapter.notifyDataSetChanged()
            }.show()
    }
}