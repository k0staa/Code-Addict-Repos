package pl.codeaddict.flutterapi.config.reactive

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity
import org.springframework.security.config.web.server.ServerHttpSecurity
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter
import org.springframework.security.web.server.SecurityWebFilterChain
import pl.codeaddict.flutterapi.config.nonreactive.KeycloakJwtAuthenticationConverter

/**
 * For Reactive web applications (WebFlux)
 **/
@Configuration
@EnableWebFluxSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ReactiveSecurityConfig {

    private val log = LoggerFactory.getLogger(this.javaClass)

    @Bean
    fun springSecurityFilterChain(http: ServerHttpSecurity): SecurityWebFilterChain? {
        log.info("Customizing security configuration (reactive)")

        http
            .csrf().disable()
            .formLogin().disable()
            .httpBasic().disable()
            .authorizeExchange()
            .anyExchange().permitAll()
            .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(ReactiveJwtAuthenticationConverterAdapter(KeycloakRealmRoleConverter()));
        return http.build()
    }
}

/*


 */

