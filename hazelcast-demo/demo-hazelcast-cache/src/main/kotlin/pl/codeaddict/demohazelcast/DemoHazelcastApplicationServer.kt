package pl.codeaddict.demohazelcast

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling
import org.springframework.scheduling.annotation.Scheduled
import kotlin.math.log


@SpringBootApplication
@EnableScheduling
class DemoHazelcastApplicationServer

fun main(args: Array<String>) {
    runApplication<DemoHazelcastApplicationServer>(*args)
}