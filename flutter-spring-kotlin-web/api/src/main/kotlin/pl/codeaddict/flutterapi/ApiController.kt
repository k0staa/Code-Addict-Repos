package pl.codeaddict.flutterapi

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ApiController {

    @CrossOrigin
    @GetMapping("/not-secured")
    fun getNonSecuredMessage() = Mono.just(ApiResponse("Server return non secured message"))
}