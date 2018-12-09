package pl.codeaddict.kafkademo.consumers

import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.Consumed
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.JoinWindows
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.KeyValueMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.kafka.support.serializer.JsonSerde
import org.springframework.stereotype.Service
import pl.codeaddict.kafkademo.config.Topics
import pl.codeaddict.kafkademo.domain.FraudData
import pl.codeaddict.kafkademo.domain.DhcpData
import pl.codeaddict.kafkademo.domain.ProxyData
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


@Service
class KafkaStreamConsumer {
    @Autowired
    var mongoTemplate: MongoTemplate? = null

    @Bean("kafkaStreamProcessing")
    fun startProcessing(builder: StreamsBuilder): KStream<String, ProxyData>? {
        val dhcpStream = builder.stream(Topics.DHCP.name, Consumed.with(Serdes.String(),
                JsonSerde(DhcpData::class.java)))
                .map<String, DhcpData>(DhcpKeyValueMapper())

        val proxyStream = builder.stream(Topics.PROXY.name, Consumed.with(Serdes.String(),
                JsonSerde(ProxyData::class.java)))
                .map<String, ProxyData>(ProxyKeyValueMapper())
        val timeNow = LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME)
        val innerJoin = proxyStream.join(dhcpStream, { proxy, dhcp -> FraudData(dhcp, proxy, timeNow) },
                JoinWindows.of(5000)
                , Serdes.String(), JsonSerde(ProxyData::class.java), JsonSerde(DhcpData::class.java))
                .filter({ _, value -> value.proxyData!!.request == "http://strange.com" })

        innerJoin.foreach { key, value ->  mongoTemplate!!.save(value)}

        // I use mongoDB in this example but you can push result to another Kafka topic.
        //innerJoin.to(Topics.FRAUD.name)
        return proxyStream
    }

    class ProxyKeyValueMapper : KeyValueMapper<String, ProxyData, KeyValue<String, ProxyData>> {
        override fun apply(key: String?, value: ProxyData): KeyValue<String, ProxyData> {
            return KeyValue<String, ProxyData>(value.src, value)
        }
    }

    class DhcpKeyValueMapper : KeyValueMapper<String, DhcpData, KeyValue<String, DhcpData>> {
        override fun apply(key: String?, value: DhcpData): KeyValue<String, DhcpData> {
            return KeyValue<String, DhcpData>(value.src, value)
        }
    }
}
