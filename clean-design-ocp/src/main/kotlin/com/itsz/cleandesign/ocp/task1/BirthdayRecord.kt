package com.itsz.cleandesign.ocp.task1

 class BirthdayRecord(
    val year: Int,
    val month: Int,
    val day: Int
): Record() {
     override fun format(): String {
         return "birthday: $year/$month/$day"
     }
 }
