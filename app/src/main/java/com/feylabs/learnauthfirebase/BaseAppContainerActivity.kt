package com.feylabs.learnauthfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class BaseAppContainerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_base_app_container)
    }
}