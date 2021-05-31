package com.burkclik.movieapp.ui.movie.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.burkclik.movieapp.common.Result
import com.burkclik.movieapp.domain.model.CreditsItem
import com.burkclik.movieapp.domain.model.MovieDetailItem
import com.burkclik.movieapp.domain.model.MovieItem
import com.burkclik.movieapp.domain.usecase.CastUseCase
import com.burkclik.movieapp.domain.usecase.MovieRelatedUseCase
import com.burkclik.movieapp.domain.usecase.MovieUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieUseCase: MovieUseCase,
    private val castUseCase: CastUseCase,
    private val movieRelatedUseCase: MovieRelatedUseCase,
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
        viewModelScope.launch {
            when (val result = movieUseCase.movieDetail(movieId)) {
                is Result.Success -> _movie.value = result.data
                is Result.Error -> Log.i("Burak", "movieDetail Error -> ${result.message}")
                is Result.Loading -> TODO()
            }
        }
    }

    private fun credits() {
        viewModelScope.launch {
            when (val result = castUseCase.credits(movieId)) {
                is Result.Success -> _creditsMovies.value = result.data!!
                is Result.Error -> Log.i("Burak", result.message!!)
                is Result.Loading -> TODO()
            }
        }
    }

    private fun relatedMovie() {
        viewModelScope.launch {
            when (val result = movieRelatedUseCase.movieRelated(movieId)) {
                is Result.Success -> _relatedMovies.value = result.data
                is Result.Error -> Log.i("Burak", "movieRelated Error -> ${result.message}")
                is Result.Loading -> TODO()
            }
        }
    }
}