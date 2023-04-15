package com.itsz.cleandesign.ocp.task1

enum class Quality {
    OFTEN_EXCEEDS_EXPECTATION, SOMETIMES_EXCEEDS_EXPECTATION, MEETS_EXPECTATION, SOMETIMES_FAILS_TO_MEET_EXPECTATION, OFTEN_FAILS_TO_MEET_EXPECTATION
}

data class FeedbackRecord(
    val criterion: String,
    val quality: Quality,
): Record()


