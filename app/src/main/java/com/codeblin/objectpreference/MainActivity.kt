package com.codeblin.objectpreference

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.codeblin.annotations.Document
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val user = UserStoreModel(generateUser())
        user.save()
        user.get().apply {
            txtFirstName.text = "First Name: $firstName"
            txtLastName.text ="Last Name: $lastName"
            txtAge.text = "Age: $age\nTransactions: "
            spinnerTransactions.adapter = ArrayAdapter<String>(this@MainActivity, android.R.layout.simple_spinner_dropdown_item, transactions.map { it.amount.toString() })
        }
    }

    fun generateUser(): User {
        val list = mutableListOf<Transaction>()
        for (i in 1..30) {
            list.add(Transaction(i, Calendar.getInstance().time, i + 1.0))
        }
        return User(1, "Jane", "Doe", 23, list)
    }
}

@Document
data class User(
    val id: Int,
    val firstName: String,
    val lastName: String,
    val age: Int,
    val transactions: List<Transaction>
)

data class Transaction(
    val id: Int,
    val date: Date,
    val amount: Double
)
