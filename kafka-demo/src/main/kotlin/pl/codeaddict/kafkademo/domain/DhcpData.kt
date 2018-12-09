package pl.codeaddict.kafkademo.domain

data class DhcpData(
        val rt: Long = 0,
        val smac: String = "",
        val shost: String = "",
        val src: String = "") {
    constructor(str: String) : this()
}