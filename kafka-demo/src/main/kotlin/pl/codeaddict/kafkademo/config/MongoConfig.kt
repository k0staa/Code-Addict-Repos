package pl.codeaddict.kafkademo.config

import com.mongodb.reactivestreams.client.MongoClient
import com.mongodb.reactivestreams.client.MongoClients
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration
import org.springframework.data.mongodb.core.CollectionOptions
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import pl.codeaddict.kafkademo.domain.FraudData


@Configuration
class MongoConfig : AbstractReactiveMongoConfiguration() {
    @Autowired
    val reactiveMongoClient: MongoClient? = null

    @Bean
    override fun reactiveMongoClient(): MongoClient {
        return MongoClients.create()
    }

    override fun getDatabaseName(): String {
        return "test"
    }

    @Bean
    override fun reactiveMongoTemplate(): ReactiveMongoTemplate {
        val reactiveMongoTemplate = ReactiveMongoTemplate(reactiveMongoClient!!, databaseName)
        if (reactiveMongoTemplate.collectionExists(FraudData::class.java).block() == false) {
            val options = CollectionOptions(4, 4, true)
            reactiveMongoTemplate.createCollection(FraudData::class.java, options)
        }
        return reactiveMongoTemplate
    }
}