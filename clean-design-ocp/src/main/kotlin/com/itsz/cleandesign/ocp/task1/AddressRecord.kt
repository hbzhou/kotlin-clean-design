package com.itsz.cleandesign.ocp.task1

class AddressRecord(
    val country: String,
    val province: String,
    val city: String,
    val street: String,
    val building: String,
    val apartment: String,
    val index: String
) : Record() {
    override fun format(): String {
        return "address: " + country + ", " + province + ", " + city + ", " +
                street + " st., " + building + " b., " + apartment + " apt., " +
                index
    }
}
