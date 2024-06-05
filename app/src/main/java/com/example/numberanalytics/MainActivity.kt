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

            performAnalyticsByHashmap()
        }
    }

    private fun performAnalyticsByHashmap() {
        listA = extractElements(binding.editTextListA.text.toString())
        listB = extractElements(binding.editTextListB.text.toString())
        listC = extractElements(binding.editTextListC.text.toString())

        val occurrenceMap = HashMap<Int, Int>()
        val allNumbers = listA + listB + listC

        for (number in allNumbers) {
            occurrenceMap[number] = occurrenceMap.getOrDefault(number, 0) + 1
        }

        val sortedMap = occurrenceMap.toList().sortedBy { (key, _) -> key }.toMap()
        intersectionItems = sortedMap.filter { (_, value) -> value == 3 }.keys.toList().toString()
        unionItems = sortedMap.keys.toList()
        maxItem = sortedMap.keys.max().toString()

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