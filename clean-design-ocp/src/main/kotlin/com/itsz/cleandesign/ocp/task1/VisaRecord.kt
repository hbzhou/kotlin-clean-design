package com.itsz.cleandesign.ocp.task1

import java.time.ZonedDateTime

data class VisaRecord(
    val country:String,
    val from: ZonedDateTime,
    val to: ZonedDateTime
): Record() {
    override fun format(): String {
        return "visa: $country, from: $from, to: $to"
    }
}