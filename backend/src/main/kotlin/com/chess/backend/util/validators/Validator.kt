package com.chess.backend.util.validators

import com.chess.backend.model.User
import com.chess.backend.model.validation.ValidationMessage
import com.chess.backend.model.validation.ValidationStatus

abstract class Validator {

    abstract fun isValid(detail: User, user: User?): ValidationMessage
    fun frameMessage(message: String, validationStatus: ValidationStatus): ValidationMessage {
       return ValidationMessage(message = message, status = validationStatus)
    }
}