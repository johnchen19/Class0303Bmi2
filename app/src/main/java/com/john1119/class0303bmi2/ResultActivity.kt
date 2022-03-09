package com.john1119.class0303bmi2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.john1119.class0303bmi2.databinding.ActivityMainBinding
import com.john1119.class0303bmi2.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private val TAG=ResultActivity::class.java.simpleName
    lateinit var binding: ActivityResultBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showBMI()
        binding.bDone.setOnClickListener {
            val name=binding.edUsername.text.toString()
            val data =Intent()
            data.putExtra(Extras.NAME,name)
            setResult(RESULT_OK,data)
            finish()
        }
    }

        private fun showBMI(){
        val bmi=intent.getFloatExtra(Extras.BMI,0f)
        Log.d(TAG, "onCreate: $bmi")
        binding.tvShowbmi.setText("$bmi")
    }

}