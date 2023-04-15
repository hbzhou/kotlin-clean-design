package com.itsz.cleandesign.ocp.task1

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.ZoneOffset
import java.time.ZonedDateTime

class RecordFormatterTest {
    private val formatter = RecordFormatter()

    @Test
    fun formatAddress() {
        val addressRecord = AddressRecord(
            country = "Ukraine",
            province = "Kharkivska",
            city = "Kharkiv",
            street = "23 Serpnya",
            building = "33",
            apartment = "N/A",
            index = "61000",
        )
        val actualResult = formatter.format(addressRecord)
        assertEquals(actualResult, "address: Ukraine, Kharkivska, Kharkiv, 23 Serpnya st., 33 b., N/A apt., 61000")
    }

    @Test
    fun formatBirthday() {
        val birthdayRecord = BirthdayRecord(
            year = 1989,
            month = 11,
            day = 27
        )
        val actualResult = formatter.format(birthdayRecord)
        assertEquals(actualResult, "birthday: 1989/11/27")
    }

    @Test
    fun formatVisa() {
        val visaRecord = VisaRecord(
            country = "Ukraine",
            from = ZonedDateTime.of(2006, 7, 23, 0, 0, 0, 0, ZoneOffset.UTC),
            to = ZonedDateTime.of(2120, 7, 23, 0, 0, 0, 0, ZoneOffset.UTC)
        )
        val actualResult = formatter.format(visaRecord)
        assertEquals(actualResult, "visa: Ukraine, from: 2006-07-23T00:00Z, to: 2120-07-23T00:00Z")
    }

    @Test
    fun formatWorkload() {
        val workloadRecord = WorkloadRecord(
            workload = mutableMapOf(1 to 100.0, 2 to 90.0, 3 to 0.0)
        )
        val fmt = formatter.format(workloadRecord)
        assertEquals(fmt, "workload: 1:100.0, 2:90.0, 3:0.0")
    }

    @Test
    fun formatSkills() {
        val skillsRecord = SkillsRecord(
            skill = "Java",
            level = Level.INTERMEDIATE
        )
        val actualResult = formatter.format(skillsRecord)
        assertEquals(actualResult, "skills: Java=INTERMEDIATE")
    }

    @Test
    fun formatFeedback() {
        val feedbackRecord = FeedbackRecord(
            criterion = "Team work",
            quality = Quality.MEETS_EXPECTATION
        )
        val actualResult = formatter.format(feedbackRecord)
        assertEquals(actualResult, "feedback: Team work=MEETS_EXPECTATION")
    }

}