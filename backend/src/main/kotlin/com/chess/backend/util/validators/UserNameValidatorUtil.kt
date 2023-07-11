package com.chess.backend.util.validators

import com.chess.backend.model.User
import com.chess.backend.model.validation.ValidationMessage
import com.chess.backend.model.validation.ValidationStatus
import org.springframework.stereotype.Component

@Component
class UserNameValidatorUtil : Validator() {
    override fun isValid(detail: User, user: User?): ValidationMessage {
        return if (detail.username == "") {
            frameMessage("Username cannot be empty.", ValidationStatus.FAILED)
        } else if (user != null) {
            frameMessage("User with same username already exists!!!", ValidationStatus.FAILED)
        } else {
            frameMessage("Valid User", ValidationStatus.SUCCESSFUL)
        }
    }
}