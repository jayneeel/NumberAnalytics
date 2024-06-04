package com.example.numberanalytics

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.numberanalytics.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private lateinit var listA : List<Int>
    private lateinit var listB : List<Int>
    private lateinit var listC : List<Int>
    private lateinit var unionItems : List<Int>
    private lateinit var intersectionItems : String
    private lateinit var maxItem : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.buttonCalculate.setOnClickListener {
            listA = binding.editTextListA.text?.split(",")!!.mapNotNull { it.trim().toIntOrNull() }
            listB = binding.editTextListB.text?.split(",")!!.mapNotNull { it.trim().toIntOrNull() }
            listC = binding.editTextListC.text?.split(",")!!.mapNotNull { it.trim().toIntOrNull() }
            unionItems = listA.union(listB).union(listC).toList()
            intersectionItems = listA.intersect(listB.toSet()).intersect(listC.toSet()).toString()
            maxItem = unionItems.maxOrNull().toString()
            binding.result1.text = intersectionItems
            binding.result2.text = unionItems.toString()
            binding.result3.text = maxItem
        }
    }
}