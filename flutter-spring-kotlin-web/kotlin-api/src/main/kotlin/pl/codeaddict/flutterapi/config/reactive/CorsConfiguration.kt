package pl.codeaddict.flutterapi.config.reactive

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer


@Configuration
@EnableWebFlux
class CorsGlobalConfiguration : WebFluxConfigurer {
    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedOrigins("*")
            .allowedMethods("PUT", "GET", "POST")
            .maxAge(3600)
    }
}