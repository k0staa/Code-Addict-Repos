package pl.codeaddict.flutterapi.config.nonreactive

import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter
import java.util.stream.Collectors
import java.util.stream.Stream

/**
 * Keycloak realm converter converts custom keycloak jwt token properties
 * and default JWT properties into granted authorities
 */
class KeycloakJwtAuthenticationConverter : Converter<Jwt, AbstractAuthenticationToken> {

    private val keycloakRealmRoleConverter = KeycloakRealmRoleConverter()
    private val defaultGrantedAuthoritiesConverter = JwtGrantedAuthoritiesConverter()

    override fun convert(source: Jwt): AbstractAuthenticationToken {
        val authorities = Stream.concat(
            defaultGrantedAuthoritiesConverter.convert(source)!!.stream(),
            keycloakRealmRoleConverter.convert(source).stream()
        ).collect(Collectors.toSet())
        return JwtAuthenticationToken(source, authorities)
    }
}