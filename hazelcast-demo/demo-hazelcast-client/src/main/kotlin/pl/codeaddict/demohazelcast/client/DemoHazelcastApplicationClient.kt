package pl.codeaddict.demohazelcast.client

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoHazelcastApplicationClient

fun main(args: Array<String>) {
	runApplication<DemoHazelcastApplicationClient>(*args)
}

