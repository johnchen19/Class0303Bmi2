package com.john1119.class0303bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.john1119.class0303bmi2.databinding.ActivityMainBinding //幫忙import出來的

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
//    lateinit等一下畫面生出來才給
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    fun bmi(view:View){//View不要自己打完因為他會幫忙import
        println("hahaha")
        var weight=binding.edWeight.text.toString().toFloat()
        var height=binding.edHeight.text.toString().toFloat()
    }
}
