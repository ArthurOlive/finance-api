package com.finance.config.jwt

import com.finance.models.UserCustomDetails
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.UnsupportedJwtException
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.SignatureException
import java.util.Date

@Component
class JwtUtils {

    private val jwtExpirationMs = 5 * 60 * 60

    @Value("\${jwt.secret}")
    private val jwtSecret: String? = null

    fun validateJwtToken(authToken: String?): Boolean {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

    fun generateJwtToken(authentication: Authentication): String {
        val employeePrincipal = authentication.principal as UserCustomDetails
        return Jwts.builder().setSubject(employeePrincipal.username).setIssuedAt(Date())
                .setExpiration(
                        Date(System.currentTimeMillis() + jwtExpirationMs * 1000)
                )
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact()
    }

    fun getEmployeeNameFromJwtToken(token: String?): String {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).body.subject
    }

    companion object {
        private val logger = LoggerFactory.getLogger(JwtUtils::class.java)
    }
}