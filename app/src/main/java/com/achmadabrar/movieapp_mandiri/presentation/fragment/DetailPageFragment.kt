package com.achmadabrar.movieapp_mandiri.presentation.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.core.base.BaseFragment
import com.achmadabrar.movieapp_mandiri.presentation.adapter.ReviewAdapter
import com.achmadabrar.movieapp_mandiri.presentation.itemdecoration.ItemDecoration
import com.achmadabrar.movieapp_mandiri.presentation.viewholder.ReviewViewHolder
import com.achmadabrar.movieapp_mandiri.presentation.viewmodel.MovieViewModel
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerSupportFragment
import com.google.android.youtube.player.YouTubePlayerSupportFragmentX
import kotlinx.android.synthetic.main.fragment_detail_page.*
import javax.inject.Inject


/**
 * Abrar
 */
class DetailPageFragment : BaseFragment(), ReviewViewHolder.Listener {

    @Inject
    lateinit var viewModel: MovieViewModel

    lateinit var adapter: ReviewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_page, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel =
            ViewModelProviders.of(activity!!, viewModelFactory).get(MovieViewModel::class.java)

        viewModel.movieSelectedLiveData.observe(viewLifecycleOwner, Observer {

            text_view_title_detail.text = it.title
        })

        viewModel.reviewPageLiveData.observe(viewLifecycleOwner, Observer {
            adapter = ReviewAdapter(it, this)
            loadRecyclerView()
        })

        val youTubePlayerFragment = YouTubePlayerSupportFragmentX.newInstance()
        val transaction= fragmentManager?.beginTransaction()
        transaction?.replace(R.id.fr_youtube, youTubePlayerFragment)
        transaction?.commit()

        viewModel.youtubeLiveData.observe(viewLifecycleOwner, Observer {
            youTubePlayerFragment.initialize(it.results[0].key, object: YouTubePlayer.OnInitializedListener{
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    youTubePlayer: YouTubePlayer?,
                    wasRestored: Boolean
                ) {
                    if (!wasRestored) {
                        youTubePlayer?.cueVideo("YE7VzlLtp-4")
                    }
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    youTubeInitializationResult: YouTubeInitializationResult?
                ) {
                    if (youTubeInitializationResult!!.isUserRecoverableError) {
                        //youTubeInitializationResult.getErrorDialog(requireActivity(), RECOVERY_DIALOG_REQUEST).show()
                        Toast.makeText(requireContext(), "errorMessage", Toast.LENGTH_LONG).show()
                    } else {
                        val errorMessage = String.format(
                            "There was an error initializing the YouTubePlayer (%1\$s)",
                            youTubeInitializationResult.toString()
                        )
                        Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_LONG).show()
                    }
                }

            })
        })

    }

    override fun onClick(url: String?) {
        Toast.makeText(requireContext(), "nanti kita buka webview ya", Toast.LENGTH_SHORT).show()
    }

    fun loadRecyclerView() {
        rv_review.adapter = adapter
        rv_review.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        rv_review.addItemDecoration(ItemDecoration())
    }


}