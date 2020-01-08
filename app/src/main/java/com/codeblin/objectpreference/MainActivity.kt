package com.codeblin.objectpreference

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.codeblin.annotations.Document

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val test = CompetitionStoreModel(listOf(
            Event("Osfp vs Paok"),
            Event("Osfp vs Paok"),
            Event("Osfp vs Paok"),
            Event("Osfp vs Paok"),
            Event("Osfp vs Paok")
        ))

        test.save()
        test.delete()
        test.get()
    }
}

data class Event(val eventName: String)

@Document
data class Competition(val events: List<Event>)
