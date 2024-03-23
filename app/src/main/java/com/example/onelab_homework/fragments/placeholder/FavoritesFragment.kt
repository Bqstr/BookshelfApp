package com.example.onelab_homework.fragments.placeholder

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import coil.Coil
import coil.compose.AsyncImage
import com.example.homework.book.Book
import com.example.onelab_homework.R
import com.example.onelab_homework.databinding.FragmentFavoritesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {
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
    lateinit var binding:FragmentFavoritesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =FragmentFavoritesBinding.inflate(layoutInflater)

        val composeView = binding.composeView


        val favorites = mutableListOf<Book>()
        composeView.setContent {


            // Compose code here
            composeView.setContent {
                val books =(arguments?.getSerializable("MYKEY") ?: mutableListOf(Book(0,"123","123","123" ,false)) )as MutableList<Book>
                Log.d("1233312132312","$books")
                Greeting(books)
            }


        }


        // Inflate the layout for this fragment
        return binding.root
    }

    @Composable
    fun Greeting(favorites:MutableList<Book>) {

        LazyColumn(){
            items(favorites.size){
                ListElement(favorites[it])
            }
        }
    }

    @Composable
    fun ListElement(item:Book){
        Row(Modifier.height(100.dp).padding(bottom =10.dp ,end =10.dp)) {
            AsyncImage(
                modifier = Modifier.fillMaxHeight().width(100.dp),
                model = item.imageUrl,
                contentDescription = null
            )
            Column(){
                Text(item.name ,Modifier.padding(top =10.dp) ,fontWeight = FontWeight.Bold , fontSize  =16.sp)
                Text(getFirst20Words(item.descr),Modifier.padding(top =10.dp))
            }

        }

    }

    fun getFirst20Words(input: String): String {
        val words = input.trim().split("\\s+".toRegex()) // Split the string into words
        val first30Words = words.take(20) // Take the first 30 words
        return first30Words.joinToString(" ") // Join the first 30 words back into a single string
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FavoritesFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FavoritesFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}