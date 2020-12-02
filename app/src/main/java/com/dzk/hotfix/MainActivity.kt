package com.dzk.hotfix

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.dzk.hotfix.utils.Utils

class MainActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        EnjoyFix.hotFix(this)
        setContentView(R.layout.activity_main)
        LoadUtil.loadClass(this)
        Utils.init()
    }
}