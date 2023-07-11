package com.chess.backend.controller

import com.chess.backend.model.JwtRequest
import com.chess.backend.model.User
import com.chess.backend.model.validation.ValidationStatus
import com.chess.backend.repository.UserMongoRepository
import com.chess.backend.service.JwtTokenGeneratorService
import com.chess.backend.service.ValidateUserService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/chess/api")
class LoginSignupController {

    private var LOGGER = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var userMongoRepository: UserMongoRepository

    @Autowired
    private lateinit var bCryptPasswordEncoder: BCryptPasswordEncoder

    @Autowired
    private lateinit var validateUserService: ValidateUserService

    @Autowired
    private lateinit var jwtTokenGeneratorService: JwtTokenGeneratorService

    @RequestMapping("/signup", method = [RequestMethod.POST])
    fun signupUser(@RequestBody user: User): ResponseEntity<*> {

        try {
            val validationResult = validateUserService.validate(user)

            return if (validationResult.status == ValidationStatus.SUCCESSFUL) {
                val password = user.password
                user.id = UUID.randomUUID()
                user.enabled = true
                user.password = this.bCryptPasswordEncoder.encode(user.password)
                val savedDetails = userMongoRepository.save(user)

                jwtTokenGeneratorService.generateToken(JwtRequest(savedDetails.username, password))
            } else {
                ResponseEntity.status (HttpStatus.CONFLICT).body(validationResult.message)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.message)
        }
    }

    @RequestMapping("/loginUser", method = [RequestMethod.POST])
    fun loginUser(@RequestBody jwtRequest: JwtRequest): ResponseEntity<*> {
        try {
            return jwtTokenGeneratorService.generateToken(jwtRequest)
        } catch (e: BadCredentialsException) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Bad credentials!!")
        }

    }
}
