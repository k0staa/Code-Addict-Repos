package pl.codeaddict.flutterapi.config.reactive

import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter
import java.util.Collections.emptyList
import java.util.Collections.emptyMap

/**
 * Keycloak realm converter converts keycloak jwt token into granted authorities
 */
class KeycloakRealmRoleConverter : JwtAuthenticationConverter() {
    private val authorityPrefix = "ROLE_"

    override fun extractAuthorities(jwt: Jwt): Collection<SimpleGrantedAuthority> {
        val authorities = jwt.claims["realm_access"] as Map<String, List<String>>? ?: emptyMap()
        return authorities.getOrDefault("roles", emptyList())
            .map { roleName -> "$authorityPrefix${roleName.toUpperCase()}" }
            .map { role -> SimpleGrantedAuthority(role) }
    }
}