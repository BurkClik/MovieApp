package com.burkclik.movieapp.ui.movie.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.burkclik.movieapp.data.MovieRepository
import com.burkclik.movieapp.data.PopularMovieRepository
import com.burkclik.movieapp.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val popularMovieRepository: PopularMovieRepository
) : ViewModel() {
    private val _onTheaterMovies = MutableLiveData<List<Movie>?>()
    val onTheaterMovies: LiveData<List<Movie>?> = _onTheaterMovies

    var popularMovies: Flow<PagingData<Movie>>? = null

    init {
        Log.i("ViewModel", "viewModel init...")
        viewModelScope.launch {
            val response = movieRepository.getTheaterMovies()
            if (response.isSuccessful) {
                _onTheaterMovies.postValue(response.body()!!.results)
            }
        }
    }

    fun getPopular(): Flow<PagingData<Movie>> {
        val lastResult = popularMovies
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<Movie>> =
            popularMovieRepository.getPopularMovies().cachedIn(viewModelScope)

        popularMovies = newResult
        return newResult
    }
}