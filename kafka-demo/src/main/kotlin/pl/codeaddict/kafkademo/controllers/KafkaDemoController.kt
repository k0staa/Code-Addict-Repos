package pl.codeaddict.kafkademo.controllers

import org.apache.kafka.clients.consumer.KafkaConsumer
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import pl.codeaddict.kafkademo.domain.FraudData
import pl.codeaddict.kafkademo.repositories.FraudDataRepository
import reactor.core.publisher.Flux
import reactor.kafka.receiver.KafkaReceiver
import java.time.Duration


@RestController
class KafkaDemoController {
    @Autowired
    val kafkaStreamConsumer: KafkaConsumer<String, FraudData>? = null
    @Autowired
    val kafkaReactorReceiver: KafkaReceiver<String, FraudData>? = null
    @Autowired
    val fraudDataRepository: FraudDataRepository? = null
    @Autowired
    var mongoTemplate: MongoTemplate? = null

    /*Just for testing. Polling data straight from Kafka is probably not a good idea.*/
    @GetMapping("/kafka/poll")
    fun streamConsumer(): String? {
        return kafkaStreamConsumer?.poll(100)
                ?.map { record -> record.value() }?.joinToString(separator = "\n\n")
    }

    @GetMapping("/kafka-reactor/frauds/stream", produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    fun streamReceiver(): Flux<FraudData>? {
        return kafkaReactorReceiver?.receiveAtmostOnce()?.map { record -> record.value() }
    }


    @GetMapping(value = "/mongo/frauds/all")
    fun dataFromMongo(): Flux<FraudData> {
        return fraudDataRepository!!.findAll()
    }

    @GetMapping(value = "/mongo/frauds/stream", produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    @ResponseBody
    fun streamFromMongo(): Flux<FraudData> {
        return fraudDataRepository!!.findWithTailableCursorBy()
                .delayElements(Duration.ofMillis(2500))
    }
}