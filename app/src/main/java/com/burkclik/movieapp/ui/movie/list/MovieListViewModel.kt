package com.burkclik.movieapp.ui.movie.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.burkclik.movieapp.common.navigation.Navigation
import com.burkclik.movieapp.data.PopularMovieRepository
import com.burkclik.movieapp.data.remote.model.MovieResponse
import com.burkclik.movieapp.domain.model.MovieItem
import com.burkclik.movieapp.domain.usecase.MovieNowPlayingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val popularMovieRepository: PopularMovieRepository,
    private val movieNowPlayingUseCase: MovieNowPlayingUseCase
) : ViewModel() {
    private val _onTheaterMovies = MutableLiveData<List<MovieItem>?>()
    val onTheaterMovies: LiveData<List<MovieItem>?> = _onTheaterMovies

    var popularMovies: Flow<PagingData<MovieResponse>>? = null

    val navigation = Navigation()

    val itemClickListener: (MovieResponse) -> Unit = {
        val action = MovieListFragmentDirections.toMovieDetail(it.id)
        navigation.navigate(action)
    }

    init {
        getMoviesNowPlaying()
    }

    private fun getMoviesNowPlaying() {
        viewModelScope.launch {
            _onTheaterMovies.value = movieNowPlayingUseCase.movieNowPlaying().data
        }
    }

    fun getPopular(): Flow<PagingData<MovieResponse>> {
        val lastResult = popularMovies
        if (lastResult != null) {
            return lastResult
        }
        val newResult: Flow<PagingData<MovieResponse>> =
            popularMovieRepository.getPopularMovies().cachedIn(viewModelScope)

        popularMovies = newResult
        return newResult
    }
}