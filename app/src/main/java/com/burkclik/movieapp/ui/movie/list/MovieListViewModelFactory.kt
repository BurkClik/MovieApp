package com.burkclik.movieapp.ui.movie.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.burkclik.movieapp.data.MovieRepository
import com.burkclik.movieapp.data.PopularMovieRepository

class MovieListViewModelFactory(
    private val movieRepository: MovieRepository,
    private val popularMovieRepository: PopularMovieRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        MovieListViewModel(movieRepository, popularMovieRepository) as T
}