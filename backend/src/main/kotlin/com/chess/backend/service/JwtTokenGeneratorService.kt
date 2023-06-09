package com.chess.backend.service

import com.chess.backend.model.JwtRequest
import com.chess.backend.model.JwtResponse
import com.chess.backend.util.JwtTokenUtil
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.*

@Service
class JwtTokenGeneratorService {

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var authenticationManager: AuthenticationManager

    @Autowired
    private lateinit var customUserDetailsService: CustomUserDetailsService

    private var logger = LoggerFactory.getLogger(this.javaClass)

    @Throws(BadCredentialsException::class, UsernameNotFoundException::class, Exception::class)
    fun generateToken(@RequestBody jwtRequest: JwtRequest): ResponseEntity<*> {

        logger.info("Generating JWT token!!")
        try {
            this.authenticationManager.authenticate(UsernamePasswordAuthenticationToken(jwtRequest.username, jwtRequest.password))
        } catch (e: UsernameNotFoundException) {
            e.printStackTrace()
            throw UsernameNotFoundException("Username Not Found Exception!!!")
        } catch (e: BadCredentialsException) {
            e.printStackTrace()
            throw BadCredentialsException("Bad Credentials!!!")
        }
        catch (e: Exception) {
            e.printStackTrace()
        }
        val userDetails = this.customUserDetailsService.loadUserByUsername(jwtRequest.username)
        val token = this.jwtTokenUtil.generateToken(userDetails.username, 2)

        return ResponseEntity.ok(JwtResponse(token))
    }
}