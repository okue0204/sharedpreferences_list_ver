package com.example.sharedpreferemces_list_ver

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.sharedpreferemces_list_ver.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val trainingManager = TrainingManager()
        val sharedPreferences = getSharedPreferences("trainingMemo", MODE_PRIVATE)

        val spinnerList: List<TrainingMenu.TrainingPosition> = listOf(
            TrainingMenu.TrainingPosition.Chest,
            TrainingMenu.TrainingPosition.Shoulder,
            TrainingMenu.TrainingPosition.Arm,
            TrainingMenu.TrainingPosition.Abs,
            TrainingMenu.TrainingPosition.Back,
            TrainingMenu.TrainingPosition.Leg
        )
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerList)
        binding.trainingPositionSpinner.adapter = spinnerAdapter

        binding.registerButton.setOnClickListener {
            val trainingPositionSpinnerIndex = binding.trainingPositionSpinner.selectedItemPosition
            val trainingPosition = TrainingMenu.TrainingPosition.trainingPositionIndexFor(trainingPositionSpinnerIndex)
            val trainingName = binding.trainingNameEditText.text.toString()
            trainingManager.trainingRegister(TrainingMenu(trainingPosition, trainingName))
            val gson = Gson()
            val trainingList = gson.toJson(trainingManager.trainingList).toString()
            sharedPreferences.edit().putString("training", trainingList).apply()
            Toast.makeText(applicationContext, "登録しました", Toast.LENGTH_SHORT).show()
        }

        binding.displayButton.setOnClickListener {
            val trainingGetInfo = sharedPreferences.getString("training", null)
            val trainingMenu = Gson().fromJson(trainingGetInfo, trainingManager.trainingList::class.java)
            binding.trainingDisplayTextView.setText(trainingMenu.toString())
        }
    }
}