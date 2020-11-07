package com.achmadabrar.movieapp_mandiri.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.core.base.BaseFragment
import com.achmadabrar.movieapp_mandiri.presentation.adapter.ListMoviePagedListAdapter
import com.achmadabrar.movieapp_mandiri.presentation.itemdecoration.ItemDecoration
import com.achmadabrar.movieapp_mandiri.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_list_movie_page.*
import javax.inject.Inject

/**
 * Abrar
 */
class ListMoviePageFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: MovieViewModel

    var adapter = ListMoviePagedListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list_movie_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MovieViewModel::class.java)

        viewModel.genreSelectedLiveData.observe(viewLifecycleOwner, Observer {
            viewModel.reloadMoviePage(it)
        })

        loadRecyclerView()
        viewModel.listMovieLiveData.observe(viewLifecycleOwner, Observer {
            adapter.submitList(viewModel.getMovie(it))
        })

        /*(activity as AppCompatActivity).supportActionBar?.setTitle(R.string.list_movie)
        (activity as AppCompatActivity).setSupportActionBar(toolbar_list_movie)*/
    }

    fun loadRecyclerView() {
        rv_list_movie.adapter = adapter
        rv_list_movie.layoutManager =
            GridLayoutManager(context, 1, LinearLayoutManager.VERTICAL, false)
        rv_list_movie.addItemDecoration(ItemDecoration())
    }

}