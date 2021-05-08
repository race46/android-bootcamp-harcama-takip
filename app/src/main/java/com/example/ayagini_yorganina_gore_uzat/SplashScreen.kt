package com.example.ayagini_yorganina_gore_uzat

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.ayagini_yorganina_gore_uzat.databinding.SplashScreenBinding
import kotlinx.android.synthetic.main.fragment_isim_degistir.*
import kotlinx.android.synthetic.main.fragment_isim_degistir.view.*
import kotlinx.android.synthetic.main.splash_screen.*

class SplashScreen : AppCompatActivity() {
    var position = 1;
    lateinit var binding : SplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = SplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        val sp = getSharedPreferences("sp", Context.MODE_PRIVATE)
        val editor = sp?.edit()
        val first_time = sp.getBoolean("first_time",false)
        editor?.apply {
            putBoolean("first_time",true)
        }?.apply()
        Handler().postDelayed({
            if(first_time)
                go_to_home()
            else{
            binding.next.visibility = View.VISIBLE
            binding.animation.setAnimation(R.raw.ilk)
            binding.textView.text = "ne kadar harcadığını hesapla"
            binding.animation.playAnimation()}
        },3000)
    }

    fun go_to_home(){
        val intent = Intent(applicationContext,HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun next(view: View){
        position++;
        when (position) {
            2 -> {binding.animation.setAnimation(R.raw.iki)
                binding.textView.text = "kalan paranı bil"
                binding.animation.playAnimation()}
            3 -> {binding.animation.setAnimation(R.raw.uc)
                binding.textView.text = "gelecek hayalini gerçekleştir"
                binding.animation.playAnimation()}
            4 -> go_to_home()
        }
    }
}