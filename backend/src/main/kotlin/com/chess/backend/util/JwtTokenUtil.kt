package com.chess.backend.util

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component
import java.util.*

const val claimsKey = "scopes"
const val signingKey = "SIGNINGKEY"
const val issuer = "ISSUER"

const val EXPIRE_DATE = 10 * 24 * 60 * 60 * 1000

@Component
class JwtTokenUtil {
    fun getClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(signingKey)
            .parseClaimsJws(token)
            .getBody()
    }

    fun generateToken(userName: String, role: Int): String {
        val claims: Claims = Jwts.claims().setSubject(userName).setAudience(role.toString())
        claims[claimsKey] = listOf(SimpleGrantedAuthority(role.toString()))

        return Jwts.builder().setClaims(claims).setIssuer(issuer).setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + EXPIRE_DATE)).signWith(SignatureAlgorithm.HS256, signingKey).compact()
    }

    fun isValidToken(claims: Claims?, userDetails: UserDetails): Boolean = claims?.let {
        return it.subject.equals(userDetails.username, true) && !(it.expiration?.before(Date()) ?: true)
    } ?: false
}