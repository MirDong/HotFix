package com.dzk.hotfix

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dzk.hotfix.utils.Utils

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Utils.init()
    }
}