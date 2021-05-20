package com.burkclik.movieapp.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.burkclik.movieapp.databinding.FragmentMovieListBinding
import com.burkclik.movieapp.ui.movie.list.genre.GenreAdapter
import com.burkclik.movieapp.ui.movie.list.genre.GenreDecorator
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterDecorator
import com.burkclik.movieapp.ui.movie.list.theater.TheaterAdapter
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModels()

    private val onTheaterAdapter = TheaterAdapter()
    private val popularAdapter = GenreAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerViewInTheater.apply {
            adapter = onTheaterAdapter
            addItemDecoration(MovieListTheaterDecorator())
        }

        binding.recyclerViewPopularMovies.apply {
            adapter = popularAdapter
            addItemDecoration(GenreDecorator())
        }

        viewModel.onTheaterMovies.observe(viewLifecycleOwner) {
            onTheaterAdapter.submitList(it)
        }

        lifecycleScope.launch {
            viewModel.getPopularMovies().collectLatest {
                popularAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}