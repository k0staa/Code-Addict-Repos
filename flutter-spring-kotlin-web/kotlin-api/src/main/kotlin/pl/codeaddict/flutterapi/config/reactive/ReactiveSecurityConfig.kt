package pl.codeaddict.flutterapi.config.reactive

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import org.springframework.web.cors.reactive.CorsWebFilter

import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource


/**
 * For Reactive web applications (WebFlux)
 **/
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
class ReactiveSecurityConfig {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        log.info("Customizing security configuration (reactive)")
        http
            .authorizeExchange { exchanges ->
                exchanges
                    .anyExchange().permitAll()
            }
            .oauth2ResourceServer { oauth2ResourceServer ->
                oauth2ResourceServer
                    .jwt { jwt ->
                        jwt.jwtAuthenticationConverter(
                            ReactiveJwtAuthenticationConverterAdapter(
                                KeycloakRealmRoleConverter()
                            )
                        )
                    }
            }
        return http.build()
    }

    @Bean
    fun corsWebFilter(): CorsWebFilter? {
        val corsConfig = CorsConfiguration()
        corsConfig.allowedOrigins = listOf("*")
        corsConfig.maxAge = 8000L
        corsConfig.allowedMethods = listOf("PUT", "GET")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", corsConfig)
        return CorsWebFilter(source)
    }
}


