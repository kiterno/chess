package com.chess.backend.util.validators

import com.chess.backend.model.User
import com.chess.backend.model.validation.ValidationMessage
import com.chess.backend.model.validation.ValidationStatus
import org.springframework.stereotype.Component

@Component
class PasswordValidatorUtil : Validator() {
    override fun isValid(detail: User, user: User?): ValidationMessage {
        if (detail.password == "" || detail.password.length < 4) {
            return frameMessage("Password length cannot be less than 4 characters!!", ValidationStatus.FAILED)
        }

        return frameMessage("Valid Password", ValidationStatus.SUCCESSFUL)
    }
}