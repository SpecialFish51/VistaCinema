package com.example.vistacinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragment(FilmsListFragment.newInstance())
    }

    private fun setFragment(fragment: Fragment) : {
        supportFragmentManager.beginTransaction()
            .replace(android.R.id.content, fragment).commit()

    }    }