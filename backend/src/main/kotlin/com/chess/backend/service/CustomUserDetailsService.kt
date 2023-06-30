package com.chess.backend.service

import com.chess.backend.util.JwtTokenUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class   CustomUserDetailsService: UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        if(username.equals("DheeruBhai")) {
            return User("DheeruBhai", "123", arrayListOf())
        } else {
            throw UsernameNotFoundException("User Not Found!!")
        }
    }
}