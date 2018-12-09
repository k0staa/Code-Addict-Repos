package pl.codeaddict.kafkademo.repositories

import reactor.core.publisher.Mono
import reactor.core.publisher.Flux
import org.springframework.data.mongodb.repository.Tailable
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import pl.codeaddict.kafkademo.domain.FraudData


interface FraudDataRepository : ReactiveCrudRepository<FraudData, String> {
    @Tailable
    fun findWithTailableCursorBy(): Flux<FraudData>

    fun findFraudById(id: String): Mono<FraudData>
}