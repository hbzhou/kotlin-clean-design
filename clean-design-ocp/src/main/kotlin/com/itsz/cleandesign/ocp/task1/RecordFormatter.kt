package com.itsz.cleandesign.ocp.task1

import java.util.stream.Collectors


class RecordFormatter {

    fun format(rec: Record): String {
        return when (rec) {
            is AddressRecord -> "address: " + rec.country + ", " + rec.province + ", " + rec.city + ", " +
                    rec.street + " st., " + rec.building + " b., " + rec.apartment + " apt., " +
                    rec.index
            is BirthdayRecord -> "birthday: " + rec.year + "/" + rec.month + "/" + rec.day
            is FeedbackRecord -> "feedback: " + rec.criterion + "=" + rec.quality
            is SkillsRecord -> "skills: " + rec.skill + "=" + rec.level
            is VisaRecord -> "visa: " + rec.country + ", from: " + rec.from + ", to: " + rec.to
            is WorkloadRecord -> rec.workload.entries
                .stream()
                .map { e -> e.key.toString() + ":" + e.value }
                .collect(Collectors.joining(", ", "workload: ", ""))
            else -> "record no: " + rec.id
        }
    }
}