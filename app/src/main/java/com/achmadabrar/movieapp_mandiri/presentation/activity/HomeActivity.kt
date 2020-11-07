package com.achmadabrar.movieapp_mandiri.presentation.activity

import android.os.Bundle
import com.achmadabrar.movieapp_mandiri.R
import com.achmadabrar.movieapp_mandiri.core.base.BaseActivity
import com.achmadabrar.movieapp_mandiri.presentation.fragment.GenrePageFragment

class HomeActivity : BaseActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, GenrePageFragment())
        transaction.addToBackStack(null)
        transaction.commit()
    }
}