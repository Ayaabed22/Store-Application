package com.example.storeapplication

<<<<<<< HEAD
import android.content.ContentValues.TAG
import android.content.Intent
import android.icu.text.Transliterator
import android.os.Bundle
import android.util.Log
=======
import android.os.Bundle
>>>>>>> origin/Map-10
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
<<<<<<< HEAD
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.app.Person.fromBundle
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.storeapplication.databinding.FragmentDeatilesBinding
import com.example.storeapplication.databinding.ProductsItemUiBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.storeapplication.GetProductResponseItem as GetProductResponseItem1
import kotlin.collections.MutableList as MutableList1


class DeatilesFragment : Fragment() {

    lateinit var binding: FragmentDeatilesBinding
    lateinit var productsRVAdapter: ProductsRVAdapter
    lateinit var ratingBar: RatingBar
    lateinit var addbutton: Button
    lateinit var productdescription: TextView
    lateinit var ProductsList:MutableList1<GetProductResponseItem1>
=======

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DeatilesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DeatilesFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
>>>>>>> origin/Map-10

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
<<<<<<< HEAD
        binding = FragmentDeatilesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getProductsFromApI()


    }


    private fun getProductsFromApI() {
        RetrofitClient.getClient().getProducts().enqueue(object :
            Callback<kotlin.collections.MutableList<com.example.storeapplication.GetProductResponseItem>> {
            override fun onResponse(
                call: Call<kotlin.collections.MutableList<com.example.storeapplication.GetProductResponseItem>>,
                response: Response<kotlin.collections.MutableList<com.example.storeapplication.GetProductResponseItem>>
            ) {
                if (response.isSuccessful) {
                    Log.i(TAG, "onResponse: " + response.body())

                }
            }

            override fun onFailure(
                call: Call<kotlin.collections.MutableList<com.example.storeapplication.GetProductResponseItem>>,
                t: Throwable
            ) {
                Log.i(TAG, "onFailure: " + t.localizedMessage)
            }

        })


    }
}




=======
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_deatiles, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment DeatilesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            DeatilesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
>>>>>>> origin/Map-10
