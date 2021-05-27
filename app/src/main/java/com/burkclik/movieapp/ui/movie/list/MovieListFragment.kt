package com.burkclik.movieapp.ui.movie.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.burkclik.movieapp.common.navigation.NavigationObserver
import com.burkclik.movieapp.databinding.FragmentMovieListBinding
import com.burkclik.movieapp.ui.movie.list.popular.PopularMovieAdapter
import com.burkclik.movieapp.ui.movie.list.popular.PopularMovieDecorator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieListFragment : Fragment() {
    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MovieListViewModel by viewModels()

    private val popularAdapter = PopularMovieAdapter()

    private val navigationObserver = NavigationObserver()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        binding.viewModel = viewModel
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navigationObserver.observer(viewModel.navigation, findNavController(), viewLifecycleOwner)

        binding.recyclerViewPopularMovies.apply {
            adapter = popularAdapter
            addItemDecoration(PopularMovieDecorator())
        }

        popularAdapter.itemClickListener = viewModel.itemClickListener

        lifecycleScope.launch {
            viewModel.getPopular().collectLatest {
                popularAdapter.submitData(it)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}