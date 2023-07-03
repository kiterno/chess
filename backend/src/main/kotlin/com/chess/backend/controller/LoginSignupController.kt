package com.chess.backend.controller

import com.chess.backend.model.User
import com.chess.backend.repository.UserMongoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
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

    @RequestMapping("/signup", method = [RequestMethod.POST])
    fun signupUser(@RequestBody user: User): ResponseEntity<*> {
        LOGGER.info("User: $user")
        user.id = UUID.randomUUID()
        user.enabled = true
        return ResponseEntity.ok(userMongoRepository.save(user))
    }
}
