package com.example.onelab_homework.fragments.favorites_page

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.savedstate.SavedStateRegistryOwner
import com.example.homework.book.Book
import com.example.onelab_homework.database.BooksRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class FavoritesPageViewModel(var repository: BooksRepository): ViewModel() {
    var books = MutableStateFlow<List<Book>>(listOf<Book>())

    fun deleteAllFavorites(){
        viewModelScope.launch {
            repository.deleteAllFavorites()
        }
        books.value = listOf()

    }

    fun getAllFavorites(){
        viewModelScope.launch {
            books.value = repository.getAllFavorites()
        }
    }

    fun change( list:MutableList<Book>){
        books.value =list
    }


    companion object{
        fun provideFactory(
            // cityRepository: CityRepository,
            //  weatherRepository: WeatherRepository,
            repository: BooksRepository,

            owner: SavedStateRegistryOwner,
            defaultArgs: Bundle? = null,
        ): AbstractSavedStateViewModelFactory =
            object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
                @Suppress("UNCHECKED_CAST")
                override fun <T : ViewModel> create(
                    key: String,
                    modelClass: Class<T>,
                    handle: SavedStateHandle
                ): T {
                    return FavoritesPageViewModel(
                        //   cityRepository,weatherRepository,
                        repository) as T
                }
            }
    }
}