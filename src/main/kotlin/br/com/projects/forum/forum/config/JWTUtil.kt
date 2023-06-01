package br.com.projects.forum.forum.config

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*

@Component
class JWTUtil(private val expiration: Long = 60000) {

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

}