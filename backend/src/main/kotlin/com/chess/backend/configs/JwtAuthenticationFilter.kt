package com.chess.backend.configs

import com.chess.backend.service.CustomUserDetailsService
import com.chess.backend.util.JwtTokenUtil
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter: OncePerRequestFilter() {

    private var LOGGER = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        // Get Jwt -> Bearer -> Validate ->

        val requestTokenHeader = request.getHeader("Authorization")
        var username: String = ""
        var jwtToken: String?

        if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7)

            try {
                username = this.jwtTokenUtil.getUsernameFromToken(jwtToken)
            } catch (e: Exception) {
                e.printStackTrace()
            }

            val userDetails = this.customUserDetailsService.loadUserByUsername(username)
            LOGGER.info("UserDetails = $userDetails")

            if (username != null && SecurityContextHolder.getContext().authentication == null) {
                val usernamePasswordAuthenticationToken = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

                usernamePasswordAuthenticationToken.details = WebAuthenticationDetailsSource().buildDetails(request)

                SecurityContextHolder.getContext().authentication = usernamePasswordAuthenticationToken

            } else {
                println("Token invalid!!")
            }
        }

        filterChain.doFilter(request, response)
    }

}