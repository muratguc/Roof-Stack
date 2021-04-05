package com.muratguc.roofstack.core

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.muratguc.roofstack.R
import com.muratguc.roofstack.ui.home.NewsListFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, NewsListFragment.newInstance())
                .commit()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Timber.e(supportFragmentManager.fragments.size.toString())
    }
}