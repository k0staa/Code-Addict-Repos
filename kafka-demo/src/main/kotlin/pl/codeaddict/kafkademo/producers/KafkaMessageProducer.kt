package pl.codeaddict.kafkademo.producers

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import pl.codeaddict.kafkademo.config.Topics


@Service
class KafkaMessageProducer {
    private val PROXY_LOG: String = "{\"rt\": 1504812385296, \"src\": \"192.168.100.252\"," +
            " \"dst\": \"192.168.100.180\",\"request\": \"http://example.com/lorem/ipsum/A!sCDn\"}"
    private val PROXY_STRANGE: String = "{\"rt\": 1504812385296, \"src\": \"192.168.100.174\"," +
            " \"dst\": \"192.168.100.252\",\"request\": \"http://strange.com\"}"
    private val DHCP_LOG: String = "{\"rt\": 1504785607870, \"smac\": \"AB:E9:24:26:6C:1C\"," +
            " \"shost\":\"station6.workstation.bank.pl\",\"src\": \"192.168.100.252\"}"
    private val DHCP_STRANGE: String = "{\"rt\": 1504785607870, \"smac\": \"AB:E9:24:26:6C:1X\", " +
            "\"shost\":\"station666.workstation.bank.pl\", \"src\": \"192.168.100.174\"}"

    @Autowired
    private val kafkaTemplate: KafkaTemplate<String, String>? = null

    fun sendMessage(msg: String, topic: String) {
        kafkaTemplate!!.send(topic, msg)
    }

    @Scheduled(fixedRate = 5000)
    fun sendDhcpLogMessage() {
        sendMessage(DHCP_LOG, Topics.DHCP.name)
    }

    @Scheduled(fixedRate = 10000)
    fun sendStrangeDhcpLogMessage() {
        sendMessage(DHCP_STRANGE, Topics.DHCP.name)
    }

    @Scheduled(fixedRate = 10000)
    fun sendStrangeProxyLogMessage() {
        sendMessage(PROXY_STRANGE, Topics.PROXY.name)
    }

    @Scheduled(fixedRate = 5000)
    fun sendProxyLogMessage() {
        sendMessage(PROXY_LOG, Topics.PROXY.name)
    }
}