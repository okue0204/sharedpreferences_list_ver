package com.example.sharedpreferemces_list_ver

data class TrainingMenu(val trainingPosition: TrainingPosition, val trainingName: String) {
    enum class TrainingPosition(val index: Int) {
        Chest(0),
        Shoulder(1),
        Arm(2),
        Abs(3),
        Back(4),
        Leg(5);

        companion object {
            fun trainingPositionIndexFor(trainingPositionSpinnerIndex: Int): TrainingPosition {
                var trainingPosition = Chest
                for (training in values()) {
                    if (training.index == trainingPositionSpinnerIndex) {
                        trainingPosition = training
                    }
                }
                return trainingPosition
            }
        }
    }
}
