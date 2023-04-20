package com.itsz.cleandesign.ocp.task1

import java.util.stream.Collectors

data class WorkloadRecord(
    val workload: Map<Int, Double>
): Record() {
    override fun format(): String {
        return workload.entries
            .stream()
            .map { e -> e.key.toString() + ":" + e.value }
            .collect(Collectors.joining(", ", "workload: ", ""))
    }
}
