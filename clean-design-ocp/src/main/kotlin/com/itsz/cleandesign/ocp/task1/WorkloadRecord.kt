package com.itsz.cleandesign.ocp.task1

data class WorkloadRecord(
    val workload: Map<Int, Double>
): Record()