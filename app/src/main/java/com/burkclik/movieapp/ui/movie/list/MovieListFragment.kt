package com.burkclik.movieapp.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.burkclik.movieapp.databinding.FragmentMovieListBinding
import com.burkclik.movieapp.ui.movie.list.theater.MovieListTheaterDecorator
import com.burkclik.movieapp.ui.movie.list.theater.TheaterAdapter

class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModels()
    private val onTheaterAdapter = TheaterAdapter()

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

        viewModel.onTheaterMovies.observe(viewLifecycleOwner) {
            onTheaterAdapter.submitList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}