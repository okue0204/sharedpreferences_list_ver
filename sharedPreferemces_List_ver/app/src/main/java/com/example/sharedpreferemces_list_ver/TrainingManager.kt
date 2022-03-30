package com.example.sharedpreferemces_list_ver

class TrainingManager(val trainingList: MutableList<TrainingMenu> = mutableListOf()) {
    fun trainingRegister(training: TrainingMenu) {
        trainingList.add(training)
    }
}