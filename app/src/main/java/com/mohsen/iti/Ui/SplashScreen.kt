package com.mohsen.iti.Ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import com.mohsen.iti.Ui.login.LoginActivity
import com.mohsen.iti.Ui.second.SecondActivity
import com.mohsin.iti.databinding.ActivitySplashScreenBinding

class SplashScreen : AppCompatActivity() {
    private lateinit var binding: ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)


        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(binding.root)

        val pref = applicationContext.getSharedPreferences("UserSharedPreferences", Context.MODE_PRIVATE)
        val isLoged = pref.getBoolean("IsLogin", false)
        val editor = pref.edit()

        Handler().postDelayed({
            if (isLoged){
                startActivity(Intent(this@SplashScreen, SecondActivity::class.java))

            }else{
                startActivity(Intent(this@SplashScreen, LoginActivity::class.java))
            }
            finish()
        }, 3000)
    }
}