package com.itsz.cleandesign.ocp.task1

data class SkillsRecord(
    val skill: String,
    val level: Level,
):Record() {
    override fun format(): String {
        return "skills: $skill=$level"
    }
}


enum class Level {
    EXPERT, ADVANCED, INTERMEDIATE, NOVICE
}