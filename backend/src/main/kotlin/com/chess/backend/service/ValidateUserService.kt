package com.chess.backend.service

import com.chess.backend.model.User
import com.chess.backend.model.validation.ValidationMessage
import com.chess.backend.model.validation.ValidationStatus
import com.chess.backend.repository.UserMongoRepository
import com.chess.backend.util.validators.EmailValidatorUtil
import com.chess.backend.util.validators.NameValidatorUtil
import com.chess.backend.util.validators.PasswordValidatorUtil
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

    @Autowired
    private lateinit var passwordValidatorUtil: PasswordValidatorUtil

    @Autowired
    private lateinit var nameValidatorUtil: NameValidatorUtil

    fun validate (user: User): ValidationMessage {
        val userDetail = userMongoRepository.findByUserName(user.username)
        val userByEmail = user.email?.let { userMongoRepository.findByEmail(it) }

        val userNameValidationResult = userNameValidatorUtil.isValid(user, userDetail)
        val emailValidationResult = emailValidatorUtil.isValid(user, userByEmail)
        val passwordValidationResult = passwordValidatorUtil.isValid(user, null)
        val nameValidatorResult = nameValidatorUtil.isValid(user, null)

        if (userNameValidationResult.status == ValidationStatus.FAILED) {
            return userNameValidationResult
        }

        if (emailValidationResult.status == ValidationStatus.FAILED) {
            return emailValidationResult
        }

        if (passwordValidationResult.status == ValidationStatus.FAILED) {
            return passwordValidationResult
        }

        if (nameValidatorResult.status == ValidationStatus.FAILED) {
            return nameValidatorResult
        }

        return ValidationMessage("Valid User.", ValidationStatus.SUCCESSFUL)
    }
}