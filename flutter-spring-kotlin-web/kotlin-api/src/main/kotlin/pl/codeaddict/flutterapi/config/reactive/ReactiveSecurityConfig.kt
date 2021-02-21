package pl.codeaddict.flutterapi.config.reactive

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain


/**
 * For Reactive web applications (WebFlux)
 **/
@EnableWebFluxSecurity
class ReactiveSecurityConfig {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        log.info("Customizing security configuration (reactive)")
        http
            .authorizeExchange { exchanges ->
                exchanges
                    .pathMatchers(HttpMethod.GET, "/secured/**").hasRole("user")
                    .pathMatchers(HttpMethod.GET, "/not-secured/**").permitAll()
                    .anyExchange().authenticated()
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
}

/*


 */

