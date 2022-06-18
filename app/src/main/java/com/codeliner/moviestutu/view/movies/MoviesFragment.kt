package com.codeliner.moviestutu.view.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.codeliner.moviestutu.R
import com.codeliner.moviestutu.databinding.FragmentMoviesBinding
import com.codeliner.moviestutu.model.GitHubSearch
import com.codeliner.moviestutu.model.Item
import kotlinx.coroutines.flow.collectLatest
import retrofit2.Response

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
        val view = inflater.inflate(R.layout.fragment_movies, container, false)

        recyclerView = binding.moviesRv
        adapter = MoviesAdapter()
        adapter.listener = {
            val bundle = Bundle()
            bundle.putSerializable("model", it)
            findNavController().navigate(R.id.action_moviesFragment_to_detailFragment, bundle)
            //Navigation.findNavController(view).navigate(MoviesFragmentDirections.actionMoviesFragmentToDetailFragment())
        }

        recyclerView.adapter = adapter
        viewModel.myMovies.observe(viewLifecycleOwner) {
                adapter.submitList(it.items)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val viewModel = ViewModelProvider(this)[MoviesViewModel::class.java]
        viewModel.getMovies()
        recyclerView = binding.moviesRv
        recyclerView.adapter = adapter

//        lifecycleScope.launchWhenCreated {
//            viewModel.pagingMoviesFlow.collectLatest {
//                adapter.submitList(it)
//            }
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        moviesBinding = null
    }

    companion object {
        private var IS_HABITS_RESET = false //если галочки сброшены

        @JvmStatic //чтобы была только одна инстанция фрагмента, если пытаемся запустить несколько раз
        fun newInstance() = MoviesFragment()
    }
}