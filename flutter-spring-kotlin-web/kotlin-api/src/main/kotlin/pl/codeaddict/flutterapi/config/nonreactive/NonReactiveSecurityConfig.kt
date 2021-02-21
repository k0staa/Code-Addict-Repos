package pl.codeaddict.flutterapi.config.nonreactive

import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

/**
 * For standard Spring Web applications (non Reactive)
 **/
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
class NonReactiveSecurityConfig : WebSecurityConfigurerAdapter() {

    private val log = LoggerFactory.getLogger(this.javaClass)

    override fun configure(http: HttpSecurity) {
        log.info("Customizing security configuration (standard)")
        http.httpBasic()
            .disable()
            .csrf()
            .disable()
            .cors()
            .disable()
            .authorizeRequests()
            .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .oauth2ResourceServer()
            .jwt()
            .jwtAuthenticationConverter(KeycloakJwtAuthenticationConverter())
        // @formatter:on
    }
}
