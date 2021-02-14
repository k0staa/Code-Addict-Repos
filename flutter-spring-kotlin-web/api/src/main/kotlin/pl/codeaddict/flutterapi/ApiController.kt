package pl.codeaddict.flutterapi

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux

@RestController
class ApiController {

    @CrossOrigin
    @GetMapping(path = ["/numbers"],
        produces = [MediaType.APPLICATION_NDJSON_VALUE])
    fun getNumbers() = Flux.range(1, 100)
}