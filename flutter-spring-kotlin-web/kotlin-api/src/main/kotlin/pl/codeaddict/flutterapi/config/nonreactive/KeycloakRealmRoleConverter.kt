package pl.codeaddict.flutterapi.config.nonreactive

import org.springframework.core.convert.converter.Converter
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.jwt.Jwt
import java.util.Collections.emptyList
import java.util.Collections.emptyMap
import java.util.stream.Collectors

/**
 * Keycloak realm converter converts keycloak jwt token into granted authorities
 */
class KeycloakRealmRoleConverter : Converter<Jwt, Collection<GrantedAuthority>> {

    private val authorityPrefix = "ROLE_"

    override fun convert(jwt: Jwt): Collection<GrantedAuthority> {
        val realmAccess = jwt.getClaim<Map<String, List<String>>>("realm_access") ?: emptyMap()
        return realmAccess.getOrDefault("roles", emptyList())
            .stream()
            .map { roleName -> "$authorityPrefix${roleName.toUpperCase()}" }
            .map { role -> SimpleGrantedAuthority(role) }
            .collect(Collectors.toSet())
    }
}