package com.achmadabrar.movieapp_mandiri.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.core.base.BaseFragment

/**
 * Abrar
 */
class DetailPageFragment : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail_page, container, false)
    }


}