package pl.codeaddict.demohazelcast

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoHazelcastApplication

fun main(args: Array<String>) {
	runApplication<DemoHazelcastApplication>(*args)
}

