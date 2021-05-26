package com.burkclik.movieapp.ui.movie.detail

import androidx.lifecycle.*
import com.burkclik.movieapp.data.MovieRepository
import com.burkclik.movieapp.model.MovieDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _movie = MutableLiveData<MovieDetail>()
    val movie: LiveData<MovieDetail> = _movie

    private val movieId: Int = savedStateHandle["movieId"]!!

    init {
        demo()
    }

    private fun demo() {
        viewModelScope.launch {
            _movie.postValue(movieRepository.getMovieDetail(movieId))
        }
    }
}