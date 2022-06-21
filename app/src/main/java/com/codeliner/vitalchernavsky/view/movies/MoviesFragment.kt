package com.codeliner.vitalchernavsky.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codeliner.vitalchernavsky.R
import com.codeliner.vitalchernavsky.databinding.FragmentMoviesBinding

class MoviesFragment : Fragment() {

    private var moviesBinding: FragmentMoviesBinding ?= null
    private val binding get() = moviesBinding!!
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MoviesAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        moviesBinding = FragmentMoviesBinding.inflate(layoutInflater, container, false)
        val viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        recyclerView = binding.moviesRv
        adapter = MoviesAdapter()

        adapter.listener = {
            val bundle = Bundle()
            bundle.putSerializable("model", it)
            // TODO: использовать safeargs... Navigation.findNavController(view).navigate(MoviesFragmentDirections.actionMoviesFragmentToDetailFragment())
            findNavController().navigate(R.id.action_moviesFragment_to_detailFragment, bundle)
        }

        recyclerView.adapter = adapter

        viewModel.myMovies.observe(viewLifecycleOwner) {
                adapter.submitList(it.items)
        }

        return binding.root

    } // onCreateView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {
        val viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovies()
        recyclerView = binding.moviesRv
        recyclerView.adapter = adapter
    }


    override fun onDestroyView() {
        super.onDestroyView()
        moviesBinding = null
    }

}