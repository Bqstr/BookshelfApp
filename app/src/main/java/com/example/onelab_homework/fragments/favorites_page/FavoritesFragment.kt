package com.example.onelab_homework.fragments.favorites_page

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import coil.compose.AsyncImage
import com.example.homework.book.Book
import com.example.onelab_homework.databinding.FragmentFavoritesBinding
import com.example.onelab_homework.App
import com.example.onelab_homework.R

/**
 * A simple [Fragment] subclass.
 * Use the [FavoritesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FavoritesFragment : Fragment() {


    lateinit var binding: FragmentFavoritesBinding

   // lateinit var books :MutableList<Book>

     val viewModel : FavoritesPageViewModel by viewModels{
        FavoritesPageViewModel.provideFactory(
            App.booksRepository,
            this
        )
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.getAllFavorites()



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFavoritesBinding.inflate(layoutInflater)



        val composeView = binding.composeView


        val favorites = mutableListOf<Book>()
        composeView.setContent {


            composeView.setContent {
                Surface {




                    val books =  mutableListOf(
                        Book(
                            0,
                            "123",
                            "123",
                            "123",
                            false
                        )
                    )
                    Log.d("1233312132312", "$books")
                    TopBar(viewModel.books.collectAsState().value.toMutableList())
                }





            }
        }




        return binding.root
    }



    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun TopBar(favorites: MutableList<Book>) {
        Scaffold(
            topBar = {
                TopAppBar(
                    colors = TopAppBarDefaults.topAppBarColors(
                        containerColor = if(isSystemInDarkTheme()) {
                            MaterialTheme.colorScheme.primaryContainer

                        }else{
                            Color.Black
                                                                                              },
                        titleContentColor = MaterialTheme.colorScheme.primary,
                    ),
                    title = {
                        Text("Favorites")
                    },
                    actions = {
                        IconButton(onClick = {
                            viewModel.deleteAllFavorites()
                        }) {
                            Icon(Icons.Default.Delete, contentDescription = "Delete")
                        }
                    }

                )
            },

            containerColor = if(isSystemInDarkTheme()) {
                Color.Black

            }else{
                MaterialTheme.colorScheme.primaryContainer
            },

        )
        {paddingVal ->

            FavoritesList(favorites = favorites,paddingVal)
            Log.d("12321321312" ,MaterialTheme.colorScheme.primaryContainer.toString()
            )

            MaterialTheme.colorScheme.primaryContainer


        }
    }

    @Composable
    fun FavoritesList(favorites:MutableList<Book>, padding:PaddingValues
    ) {

        if(favorites.size==0){
            Log.d("sssss" ,favorites.size.toString())

            Box(modifier =Modifier.padding(padding).fillMaxSize()){
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {


                    Text(text="No Favorites",modifier = Modifier.padding(bottom = 10.dp), color =Color.White ,fontSize =40.sp)
                    Image(
                        modifier =Modifier.align(Alignment.CenterHorizontally),
                        painter = painterResource(id = R.drawable.white_book),
                        contentDescription = "empty",
                    )
                }
            }
        }
        else {
            LazyColumn(modifier = Modifier.padding(padding)
            ) {
                Log.d("sssss" ,favorites.size.toString())
                items(favorites.size) {
                    ListElement(favorites[it])
                }
            }
        }
    }

    @Composable
    fun ListElement(item: Book){
        Row(
            Modifier
                .height(100.dp)
                .padding(bottom = 10.dp, end = 10.dp).clickable {
                    val bundle =Bundle()
                    //serilizable медленнее, но легче
                    bundle.putSerializable("KEY" ,item)
                    findNavController().navigate(R.id.action_favoritesFragment_to_elementDescriptionPage, bundle)
                } , ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(100.dp),
                model = item.imageUrl,
                contentDescription = null
            )
            Column() {
                Text(
                    item.name,
                    Modifier.padding(top = 10.dp),
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color =if(isSystemInDarkTheme()){
                        Color.White
                    }else{ Color.Black}
                )
                Text(getFirst20Words(item.descr), Modifier.padding(top = 10.dp),color =if(isSystemInDarkTheme()){
                    Color.White
                }else{ Color.Black})
            }

        }

    }

    fun getFirst20Words(input: String): String {
        val words = input.trim().split("\\s+".toRegex()) // Split the string into words
        val first30Words = words.take(20) // Take the first 30 words
        return first30Words.joinToString(" ")
    }




}