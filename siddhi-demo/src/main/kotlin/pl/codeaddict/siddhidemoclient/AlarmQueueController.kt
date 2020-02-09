package pl.codeaddict.siddhidemoclient

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import java.time.Duration.ofMillis

@RestController
class AlarmQueueController {

    @GetMapping(value = ["/alarms/stream"], produces = [MediaType.TEXT_EVENT_STREAM_VALUE])
    @ResponseBody
    fun streamAlarmQueue(): Flux<String> {
        return Flux
                .interval(ofMillis(1100))
                .map { "NEW VALUE: " + "value" }
    }
}