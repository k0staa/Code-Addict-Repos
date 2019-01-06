package pl.codeaddict.demohazelcast.cache

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling


@SpringBootApplication
@EnableScheduling
class DemoHazelcastApplicationCache

fun main(args: Array<String>) {
    runApplication<DemoHazelcastApplicationCache>(*args)
}