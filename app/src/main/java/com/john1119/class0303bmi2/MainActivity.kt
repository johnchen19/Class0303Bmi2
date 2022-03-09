package com.john1119.class0303bmi2

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContract
import androidx.appcompat.app.AlertDialog
import com.john1119.class0303bmi2.databinding.ActivityMainBinding //幫忙import出來的
import java.util.*

class MainActivity : AppCompatActivity() {
    private val REQUEST_DISPLAY_BMI= 16
    private val TAG=MainActivity::class.java.simpleName
    lateinit var binding:ActivityMainBinding
//    lateinit等一下畫面生出來才給
    var launcher = registerForActivityResult(NameContract()){name->
    //Toast.makeText(this, name, Toast.LENGTH_LONG).show()
        Log.d(TAG, "$name")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d(TAG, "onCreate: ")
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
            .setPositiveButton("OK"
                //anonymous
//                ,object : DialogInterface.OnClickListener {
//                    override fun onClick(p0: DialogInterface?, p1: Int) {
//                        //TODO("Not yet implemented")
//                    }
//
//                }
            //變成下面lambda
//                ,{dialog,which->
//
//                }
            //變成下面擺在括號外
                ){d,w->
                binding.edWeight.setText("")
                binding.edHeight.setText("")
            }
            //.show()
        val intent=Intent(this,ResultActivity::class.java)
        intent.putExtra("BMI",bmi)//第一個參數是標籤
//        startActivity(intent)
//        startActivityForResult(intent,REQUEST_DISPLAY_BMI)
        launcher.launch(bmi)

//        binding.tvBmi.text="Your BMI is $bmi"
//        if(bmi>25){
//            binding.tvBmi.setTextColor(Color.RED)
//        }else if(bmi<15){
//            binding.tvBmi.setTextColor(Color.BLUE)
//        }else{
//            binding.tvBmi.setTextColor(Color.BLACK)
//        }

        //hw bmi>25 red , bmi<15 blue

        //AlertDialog原始的方式應該寫成
//        val builder=AlertDialog.Builder(this)
//        builder.setTitle("BMI")
//        builder.setMessage(bmi.toString())
//        builder.setPositiveButton("OK",null)
//        val dialog=builder.create()
//        dialog.show()
    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//        Log.d(TAG, "onActivityResult: ")
//        if(requestCode==REQUEST_DISPLAY_BMI && resultCode== RESULT_OK){
//            Log.d(TAG, "back from ResultActivity")
//        }
//    }
    class NameContract:ActivityResultContract<Float,String>(){//<傳出的資料型態,返回資料的格式>
        //abstract抽象




        override fun parseResult(resultCode: Int, intent: Intent?): String {
            if(resultCode== RESULT_OK){
                val name=intent?.getStringExtra(Extras.NAME)
                return name!!
            } else{
                return "No name"
            }
        }
        override fun createIntent(context: Context, input: Float?): Intent {
            val data = Intent(context,ResultActivity::class.java).putExtra(Extras.BMI,input)
            return data
        }
}

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: ")
    }

    override fun onRestart() {
        super.onRestart()
        Log.d(TAG, "onRestart: ")
    }
}
