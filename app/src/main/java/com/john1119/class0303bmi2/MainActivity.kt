package com.john1119.class0303bmi2

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.john1119.class0303bmi2.databinding.ActivityMainBinding //幫忙import出來的
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
//    lateinit等一下畫面生出來才給
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    //匿名函式 P130
    // 下三者相同
//        binding.bHelp.setOnClickListener ({ Log.d("MainActivity","NeedHelp")})
//        binding.bHelp.setOnClickListener (){ Log.d("MainActivity","NeedHelp")}//小括號最後一個參數lambda語法可以拿到外面
        binding.bHelp.setOnClickListener { Log.d("MainActivity","NeedHelp") }//唯一的參數拿出去裡面沒有東西小括號可以砍掉
}
    fun bmi(view:View){//View不要自己打完因為他會幫忙import
//        println("hahaha")
        var weight=binding.edWeight.text.toString().toFloat()
        var height=binding.edHeight.text.toString().toFloat()
        height=if(height>=2.6) height/100 else height
//        println("bmi is ${weight/(height*height)}")
        var bmi = weight/(height*height)
        bmi=((bmi*100).toInt())/100.0F
        Log.d("MainActivity BMI",bmi.toString())//.i :info ; .d : debug  ; .w : warning ; .e : error
        //正規tag寫類別名稱,msg寫事件名稱(需要是一個String)，如本處應寫ＭainActivity
        Toast.makeText(this,bmi.toString(),Toast.LENGTH_LONG).show()
        //context畫面,是的Activity上層父類別,CharSequence是String的父類別
        // ,duration寫Toast.LENGTH_LONG是為了可讀性，全大寫是常數
        AlertDialog.Builder(this)
            .setMessage(bmi.toString())
            .setTitle("Your BMI")
            .setPositiveButton("OK",null)
            //.show()
        binding.tvBmi.text="Your BMI is $bmi"
        if(bmi>25){
            binding.tvBmi.setTextColor(Color.RED)
        }else if(bmi<15){
            binding.tvBmi.setTextColor(Color.BLUE)
        }else{
            binding.tvBmi.setTextColor(Color.BLACK)
        }

        //hw bmi>25 red , bmi<15 blue

        //AlertDialog原始的方式應該寫成
//        val builder=AlertDialog.Builder(this)
//        builder.setTitle("BMI")
//        builder.setMessage(bmi.toString())
//        builder.setPositiveButton("OK",null)
//        val dialog=builder.create()
//        dialog.show()
    }
}
