package com.burkclik.movieapp.ui.movie.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burkclik.movieapp.data.MovieRepository
import com.burkclik.movieapp.model.Movie
import kotlinx.coroutines.launch

class MovieListViewModel() : ViewModel() {
    private val _onTheaterMovies = MutableLiveData<List<Movie>>()
    val onTheaterMovies: LiveData<List<Movie>> = _onTheaterMovies

    private val _moviesByGenre = MutableLiveData<List<Movie>>()
    val moviesByGenre: LiveData<List<Movie>> = _moviesByGenre

    private val movieRepository = MovieRepository()

    init {
        Log.i("Burak", "viewModel init...")
        viewModelScope.launch {
            _moviesByGenre.postValue(movieRepository.getMovieByGenre().body()!!.results)
            _onTheaterMovies.postValue(movieRepository.getTheaterMovies().body()!!.results)
        }
    }
}