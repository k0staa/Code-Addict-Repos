package pl.codeaddict.flutterapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FlutterApiApplication

fun main(args: Array<String>) {
	runApplication<FlutterApiApplication>(*args)
}
