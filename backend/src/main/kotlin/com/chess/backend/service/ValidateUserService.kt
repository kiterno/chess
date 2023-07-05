package com.chess.backend.service

import com.chess.backend.model.User
import com.chess.backend.model.validation.ValidationMessage
import com.chess.backend.model.validation.ValidationStatus
import com.chess.backend.repository.UserMongoRepository
import com.chess.backend.util.validators.EmailValidatorUtil
import com.chess.backend.util.validators.UserNameValidatorUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ValidateUserService {

    @Autowired
    private lateinit var userMongoRepository: UserMongoRepository

    @Autowired
    private lateinit var emailValidatorUtil: EmailValidatorUtil

    @Autowired
    private lateinit var userNameValidatorUtil: UserNameValidatorUtil

    fun validate (user: User): ValidationMessage {
        val userDetail = userMongoRepository.findByUserName(user.username)
        val userByEmail = user.email?.let { userMongoRepository.findByEmail(it) }

        val userNameValidationResult = userNameValidatorUtil.isValid(user, userDetail)
        val emailValidationResult = emailValidatorUtil.isValid(user, userByEmail)
        if (userNameValidationResult.status == ValidationStatus.FAILED) {
            return userNameValidationResult
        }

        if (emailValidationResult.status == ValidationStatus.FAILED) {
            return emailValidationResult
        }

        return ValidationMessage("Valid User.", ValidationStatus.SUCCESSFUL)
    }
}