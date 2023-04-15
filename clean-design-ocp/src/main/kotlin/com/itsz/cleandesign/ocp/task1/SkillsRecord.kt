package com.itsz.cleandesign.ocp.task1

data class SkillsRecord(
    val skill: String,
    val level: Level,
):Record()


enum class Level {
    EXPERT, ADVANCED, INTERMEDIATE, NOVICE
}