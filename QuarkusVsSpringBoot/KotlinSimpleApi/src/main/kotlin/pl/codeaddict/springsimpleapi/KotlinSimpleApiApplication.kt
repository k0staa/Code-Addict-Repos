package pl.codeaddict.springsimpleapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinSimpleApiApplication

fun main(args: Array<String>) {
	runApplication<KotlinSimpleApiApplication>(*args)
}
