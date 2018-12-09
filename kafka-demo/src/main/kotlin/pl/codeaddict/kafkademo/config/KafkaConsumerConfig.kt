package pl.codeaddict.kafkademo.config

import org.apache.kafka.clients.consumer.ConsumerConfig
import org.apache.kafka.clients.consumer.KafkaConsumer
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.common.serialization.StringDeserializer
import org.apache.kafka.streams.StreamsConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.KafkaStreamsDefaultConfiguration
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerde
import pl.codeaddict.kafkademo.domain.FraudData
import pl.codeaddict.kafkademo.domain.ProxyData
import reactor.kafka.receiver.KafkaReceiver
import reactor.kafka.receiver.ReceiverOptions
import java.util.*


@Configuration
class KafkaConsumerConfig {
    @Value("\${delivery-stats.stream.threads:1}")
    private val threads: Int = 1

    @Value("\${delivery-stats.kafka.replication-factor:1}")
    private val replicationFactor: Int = 1

    @Value("\${messaging.kafka-dp.brokers.url:localhost:9092}")
    private val brokersUrl: String? = null

    @Bean
    fun consumerProps(): HashMap<String, Any> {
        val props = HashMap<String, Any>()
        props[ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG] = brokersUrl as String
        props[ConsumerConfig.GROUP_ID_CONFIG] = "fraudDetectionGroup"
        props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
        props[JsonDeserializer.VALUE_DEFAULT_TYPE] = FraudData::class.java
        props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = JsonDeserializer(FraudData::class.java).javaClass
        return props
    }

    @Bean
    fun kafkaConsumer(): KafkaConsumer<String, FraudData> {
        val consumer: KafkaConsumer<String, FraudData> = KafkaConsumer(consumerProps())
        consumer.subscribe(arrayListOf(Topics.FRAUD.name))
        return consumer
    }

    @Bean
    fun kafkaReceiver(): KafkaReceiver<String,FraudData> {
        val receiverOptions: ReceiverOptions<String, FraudData> = ReceiverOptions.create(consumerProps())
        receiverOptions.subscription(Collections.singleton(Topics.FRAUD.name))
        return KafkaReceiver.create(receiverOptions)
    }

    @Bean(name = arrayOf(KafkaStreamsDefaultConfiguration.DEFAULT_STREAMS_CONFIG_BEAN_NAME))
    fun kStreamsConfig(): StreamsConfig {
        val props = HashMap<String, Any>()
        props[StreamsConfig.APPLICATION_ID_CONFIG] = "test-streams"
        props[StreamsConfig.BOOTSTRAP_SERVERS_CONFIG] = brokersUrl as String
        props[StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG] = Serdes.String().javaClass
        props[StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG] = JsonSerde(ProxyData::class.java).javaClass
        props[JsonDeserializer.DEFAULT_KEY_TYPE] = String::class.java
        props[JsonDeserializer.DEFAULT_VALUE_TYPE] = ProxyData::class.java
        return StreamsConfig(props)
    }
}