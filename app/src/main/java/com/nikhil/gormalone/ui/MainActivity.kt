package com.nikhil.gormalone.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nikhil.gormalone.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
    }
}