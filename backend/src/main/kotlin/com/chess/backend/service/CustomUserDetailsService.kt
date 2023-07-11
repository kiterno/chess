package com.chess.backend.service

import com.chess.backend.model.CustomUserDetails
import com.chess.backend.repository.UserMongoRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService: UserDetailsService {
    private var logger = LoggerFactory.getLogger(this.javaClass)

    @Autowired
    private lateinit var userMongoRepository: UserMongoRepository

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userMongoRepository.findByUserName(username)

        if (user == null) {
            throw UsernameNotFoundException("User not Found!!")
        } else {
            return CustomUserDetails(user)
        }
    }
}