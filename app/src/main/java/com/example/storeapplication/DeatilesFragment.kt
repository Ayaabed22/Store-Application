import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.storeapplication.ProductsRVAdapter
import com.example.storeapplication.databinding.FragmentDeatilesBinding


    class DeatilesFragment : Fragment() {
lateinit var binding: FragmentDeatilesBinding

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {
            binding= FragmentDeatilesBinding.inflate(inflater,container,false)
             return binding.root
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)

        }
        }









