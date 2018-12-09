package pl.codeaddict.kafkademo.domain

data class ProxyData(
        var rt: Long = 0,
        var src: String = "",
        var dst: String = "",
        var request: String = "") {
    constructor(str: String) : this() {}
}