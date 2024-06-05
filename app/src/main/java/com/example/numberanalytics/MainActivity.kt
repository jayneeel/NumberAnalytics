package com.example.numberanalytics

import android.os.Bundle
import android.text.InputFilter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.numberanalytics.databinding.ActivityMainBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

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
        window.statusBarColor = getColor(R.color.primary_accent)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val filter = InputFilter { source, start, end, dest, _, _ ->
            for (i in start until end) {
                if (!Character.isDigit(source[i]) && source[i] != ',') {
                    showErrorDialog()
                    return@InputFilter ""
                }
            }
            null
        }

        binding.editTextListA.filters = arrayOf(filter)
        binding.editTextListB.filters = arrayOf(filter)
        binding.editTextListC.filters = arrayOf(filter)

        binding.buttonCalculate.setOnClickListener {
            performAnalytics()
        }
    }

    private fun performAnalytics() {
        listA = extractElements(binding.editTextListA.text.toString())
        listB = extractElements(binding.editTextListB.text.toString())
        listC = extractElements(binding.editTextListC.text.toString())

        listA.union(listB).union(listC).toList().also { unionItems = it }
        listA.intersect(listB.toSet()).intersect(listC.toSet()).toString()
            .also { intersectionItems = it }
        unionItems.maxOrNull().toString().also { maxItem = it }


        binding.result1.text = intersectionItems
        binding.result2.text = unionItems.toString()
        binding.result3.text = maxItem
    }

    private fun showErrorDialog() {
        val builder = MaterialAlertDialogBuilder(this)
        builder.setTitle("Invalid Input")
            .setMessage("Only Numbers and , are allowed!")
            .setPositiveButton("OK", null)
            .show()

    }

    private fun extractElements(str: String): List<Int> {
        return str.split(",").mapNotNull { it.trim().toIntOrNull() }
    }
}