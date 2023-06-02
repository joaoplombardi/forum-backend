package br.com.projects.forum.forum.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JWTUtil(private val expiration: Long = 6000000) {

    @Value("~\${jwt.secret}")
    private lateinit var secret: String

    fun generateToken(username: String): String? {
        return Jwts.builder()
            .setSubject(username)
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(getSecretKey(), SignatureAlgorithm.HS512)
            .compact()
    }

    private fun getSecretKey(): Key {
        val bytes = Decoders.BASE64.decode(this.secret)
        return Keys.hmacShaKeyFor(bytes)
    }

    fun isValid(token: String?): Boolean {
        return try {
            Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token)
            true
        }catch (e: IllegalArgumentException){
            false
        }
    }

    fun getAuthentication(token: String?) : Authentication {
        val username = Jwts.parserBuilder().setSigningKey(getSecretKey()).build().parseClaimsJws(token).body.subject
        return UsernamePasswordAuthenticationToken(username, null, null)
    }

}