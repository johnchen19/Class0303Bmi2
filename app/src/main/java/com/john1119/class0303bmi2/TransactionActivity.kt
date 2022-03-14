package com.john1119.class0303bmi2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.john1119.class0303bmi2.databinding.ActivityTransactionBinding
import com.john1119.class0303bmi2.databinding.RowTransactionBinding
import org.json.JSONArray
import org.json.JSONObject
import java.net.URL
import kotlin.concurrent.thread

class TransactionActivity : AppCompatActivity() {
    lateinit var binding: ActivityTransactionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransactionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.recycler.hasFixedSize()
        binding.recycler.layoutManager = LinearLayoutManager(this)


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
            runOnUiThread {//only main thread can contral ui
                binding.recycler.adapter = object : RecyclerView.Adapter<TranViewHolder>() {
                    //需回傳TranViewHolder，裡面塞打好氣的view
                    override fun onCreateViewHolder(//因為要存取transactions變數所以寫在thread裏面的匿名類別比較方便
                        parent: ViewGroup,
                        viewType: Int
                    ): TranViewHolder {
                        val binding = RowTransactionBinding.inflate(layoutInflater, parent, false)
                        return TranViewHolder(binding)
                    }
                    //指定顯示的值，position會自動幫忙做類似for in，把值塞入每一列
                    override fun onBindViewHolder(holder: TranViewHolder, position: Int) {
                        val tran = transactions[position]
//                        val tran = transactions.get(position)
                        holder.account.setText(tran.account.toString())
                        holder.amount.setText(tran.amount.toString())
                        holder.date.setText(tran.date.toString())
                        holder.type.setText(tran.type.toString())

                    }
                    //給資料總數
                    override fun getItemCount(): Int {
                        return transactions.size
                    }

                }
            }
        }


    }
    //adapter的createViewHolder會要viewholder所以要先寫
    inner class TranViewHolder(val binding: RowTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val account = binding.tranAccount
        val amount = binding.tranAmount
        val date = binding.tranDate
        val type = binding.tranType
    }
}

//處理JSON時需用到類別，但可以用plugin會比較快
data class Transaction(val account: String, val date: String, val amount: Int, val type: Int) {

}