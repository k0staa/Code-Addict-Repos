package pl.codeaddict.demohazelcast.client

import com.hazelcast.core.HazelcastInstance
import com.hazelcast.core.IMap
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration
import java.time.Duration.ofMillis



@RestController
class HazelcastClientController {
    val key = "MY_KEY"

    @Autowired
    private val hazelcastInstance: HazelcastInstance? = null

    @GetMapping(value = "/hazelcast/stream", produces = arrayOf(MediaType.TEXT_EVENT_STREAM_VALUE))
    @ResponseBody
    fun streamHazelcastMap(): Flux<String> {
        val hazelcastMap: IMap<String, Int> = hazelcastInstance?.getMap("my-map")!!
        return Flux
                .interval(Duration.ofMillis(1100))
                .map { tick ->  "NEW VALUE: " + hazelcastMap.get(key) }
    }
}