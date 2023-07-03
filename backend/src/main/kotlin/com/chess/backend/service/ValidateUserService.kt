package com.chess.backend.service

import com.chess.backend.model.User
import org.springframework.stereotype.Service

@Service
class ValidateUserService {
    fun validate (user: User): Boolean {
        return user.username != null
    }
}