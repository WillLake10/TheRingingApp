package com.example.theringingapp

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.theringingapp.data.MethodListAdapter
import com.example.theringingapp.data.MethodViewModel
import com.example.theringingapp.data.dto.Method
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private val newMethodActivityRequestCode = 1
    private lateinit var methodViewModel: MethodViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        val adapter = MethodListAdapter(this)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        methodViewModel = ViewModelProvider(this).get(MethodViewModel::class.java)

        methodViewModel.allWords.observe(this, Observer { methods ->
            methods?.let { adapter.setMethods(it) }
        })

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            val intent = Intent(this@MainActivity, NewMethodActivity::class.java)
            startActivityForResult(intent, newMethodActivityRequestCode)
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == newMethodActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.getStringExtra(NewMethodActivity.EXTRA_REPLY)?.let {
                val v = it.split("/").toTypedArray()
                val method = Method(v[0],v[1],v[2].toInt())
                methodViewModel.insert(method)
            }
        } else {
            Toast.makeText(
                applicationContext,
                R.string.empty_not_saved,
                Toast.LENGTH_LONG).show()
        }
    }
}
