package pl.codeaddict.kafkademo.domain

import org.springframework.data.mongodb.core.mapping.Document

@Document
data class FraudData(
        var dhcpData: DhcpData? = null,
        var proxyData: ProxyData? = null,
        val timeStr: String? = "",
        var id: String? = null
        )