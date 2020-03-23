package com.ybbae.diaryofservant.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.ui.core.setContent
import com.ybbae.diaryofservant.ui.login.SignInActivity
import com.ybbae.diaryofservant.ui.main.AppMain

class MainActivity : AppCompatActivity()
{
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContent {
            AppMain() {
                startActivity(Intent(this, SignInActivity.javaClass))
            }
        }
    }
}