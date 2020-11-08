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
import com.achmadabrar.movieapp_mandiri.data.model.Result
import com.achmadabrar.movieapp_mandiri.data.network.NetworkState
import com.achmadabrar.movieapp_mandiri.presentation.adapter.ListMovieAdapter
import com.achmadabrar.movieapp_mandiri.presentation.adapter.ListMoviePagedListAdapter
import com.achmadabrar.movieapp_mandiri.presentation.itemdecoration.ItemDecoration
import com.achmadabrar.movieapp_mandiri.presentation.viewholder.ListMovieViewHolder
import com.achmadabrar.movieapp_mandiri.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_list_movie_page.*
import javax.inject.Inject

/**
 * Abrar
 */
class ListMoviePageFragment : BaseFragment(), ListMovieViewHolder.Listener {

    @Inject
    lateinit var viewModel: MovieViewModel

    lateinit var adapter: ListMovieAdapter

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

        viewModel.listMovie.observe(viewLifecycleOwner, Observer {
            if (!it.isNullOrEmpty()) {
                adapter = ListMovieAdapter(it, this)
                loadRecyclerView()
            }
        })
    }

    fun loadRecyclerView() {
        rv_list_movie.adapter = adapter
        rv_list_movie.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_list_movie.addItemDecoration(ItemDecoration())
    }

    override fun onClickMovie(result: Result?) {
        viewModel.getSelectedMovie(result)
        viewModel.getReviewFromApi(result?.id?.toInt())
        viewModel.getYoutubeKey(result?.id?.toInt())
        val transaction = fragmentManager?.beginTransaction()
        transaction?.replace(R.id.frameLayout, DetailPageFragment())
        transaction?.addToBackStack(null)
        transaction?.commit()
    }
}