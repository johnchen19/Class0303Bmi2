package com.john1119.class0303bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.gson.Gson
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import kotlin.concurrent.thread

class TransactionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_transaction)
        thread {
            val json = URL("https://atm201605.appspot.com/h").readText()


//            val array = JSONArray(json)
//            val transactions= mutableListOf<Transaction>()
//            for (i in 0 until array.length()) {
//                val obj: JSONObject = array.getJSONObject(i)
//                val amount=obj.getInt("amount")//土法煉鋼可得出每個值
//                val date = obj.getString("date")
//                val type=obj.getInt("type")
//                val account=obj.getString("account")
//                val tran=Transaction(account,date,amount,type)
//                transactions.add(tran)

            val gson = Gson()
            val transactions = gson.fromJson(json, Array<Transaction>::class.java).toList()
            transactions.forEach {
                println(it)
            }
        }
    }
}


data class Transaction(val account: String, val date: String, val amount: Int, val type: Int) {

}