package pl.codeaddict.flutterapi

import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono

@RestController
class ApiController {

    @PreAuthorize("permitAll()")
    @GetMapping("/not-secured")
    fun getNonSecuredMessage() = Mono.just(ApiResponse("Server return non secured message"))

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/secured")
    fun getSecuredMessage() = Mono.just(ApiResponse("Server return SECURED message"))
}