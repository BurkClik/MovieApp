package com.burkclik.movieapp.ui.movie.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burkclik.movieapp.common.Result
import com.burkclik.movieapp.domain.MovieUseCase
import com.burkclik.movieapp.domain.model.CreditsItem
import com.burkclik.movieapp.domain.model.MovieDetailItem
import com.burkclik.movieapp.domain.model.MovieItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _movie = MutableLiveData<MovieDetailItem?>()
    val movie: LiveData<MovieDetailItem?> = _movie

    private val _relatedMovies = MutableLiveData<List<MovieItem>?>()
    val relatedMovies: LiveData<List<MovieItem>?> = _relatedMovies

    private val _creditsMovies = MutableLiveData<List<CreditsItem>>()
    val creditsMovie: LiveData<List<CreditsItem>> = _creditsMovies

    private val movieId: Int = savedStateHandle["movieId"]!!

    init {
        relatedMovie()
        credits()
        movieDetail()
    }

    private fun movieDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = movieUseCase.movieDetail(movieId)
            launch(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        if (result.data != null) {
                            _movie.value = result.data
                        } else {
                            _movie.value = null
                        }
                    }
                    is Result.Error -> Log.i("Burak", result.message!!)
                    is Result.Loading -> TODO()
                }
            }
        }
    }

    private fun credits() {
        viewModelScope.launch {
            Log.i("Burak", Thread.currentThread().name)
            val result = movieUseCase.credits(movieId)
            _creditsMovies.value = result
        }
    }

    private fun relatedMovie() {
        viewModelScope.launch(Dispatchers.IO) {
            Log.i("Burak", Thread.currentThread().name)
            val result = movieUseCase.relatedMovies(movieId)
            launch(Dispatchers.Main) {
                when (result) {
                    is Result.Success -> {
                        if (result.data != null) {
                            val movies = result.data
                            _relatedMovies.value = movies
                        } else {
                            _relatedMovies.value = null
                        }
                    }
                    is Result.Error -> Log.i("Burak", result.message!!)
                    is Result.Loading -> TODO()
                }
            }
        }
    }
}