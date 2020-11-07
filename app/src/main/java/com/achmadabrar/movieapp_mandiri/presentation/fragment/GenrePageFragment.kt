package com.achmadabrar.movieapp_mandiri.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.core.base.BaseFragment
import com.achmadabrar.movieapp_mandiri.data.model.Genre
import com.achmadabrar.movieapp_mandiri.presentation.adapter.GenrePageAdapter
import com.achmadabrar.movieapp_mandiri.presentation.itemdecoration.ItemDecoration
import com.achmadabrar.movieapp_mandiri.presentation.viewholder.GenreViewHolder
import com.achmadabrar.movieapp_mandiri.presentation.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_genre_page.*
import kotlinx.android.synthetic.main.fragment_list_movie_page.*
import javax.inject.Inject


/**
 * Abrar
 */

class GenrePageFragment : BaseFragment(), GenreViewHolder.OnClickListener {

    @Inject
    lateinit var viewModel: MovieViewModel
    lateinit var adapter: GenrePageAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_genre_page, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MovieViewModel::class.java)

        viewModel.genrePageLiveData.observe(viewLifecycleOwner, Observer {
            if (!it.genres.isNullOrEmpty()) {
                adapter = GenrePageAdapter(it.genres, this)
                loadRecyclerView()
            }
        })
        (activity as AppCompatActivity).supportActionBar?.setTitle(R.string.list_genre)
        (activity as AppCompatActivity).setSupportActionBar(toolbar_genre_page)
    }

    private fun loadRecyclerView() {
        rv_genre_page.adapter = adapter
        rv_genre_page.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_genre_page.addItemDecoration(ItemDecoration())
    }

    override fun onClickGenre(genres: Genre?) {
        viewModel.getGenreSelected(genres)
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.frameLayout, ListMoviePageFragment())
        fragmentTransaction?.addToBackStack("genre")
        fragmentTransaction?.commit()
    }

}