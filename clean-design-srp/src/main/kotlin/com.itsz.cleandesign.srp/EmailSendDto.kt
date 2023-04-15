package com.itsz.cleandesign.srp

data class EmailSendDto(
    val from: String,
    val to: String,
    val subject: String,
    val content: String
)
