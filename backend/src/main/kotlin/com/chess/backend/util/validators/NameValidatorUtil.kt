package com.chess.backend.util.validators

import com.chess.backend.model.User
import com.chess.backend.model.validation.ValidationMessage
import com.chess.backend.model.validation.ValidationStatus

class NameValidatorUtil: Validator() {
    override fun isValid(detail: User, user: User?): ValidationMessage {
        if (detail.firstName == "") {
            return frameMessage("First Name cannot be empty!!", ValidationStatus.FAILED)
        }

        return frameMessage("Valid Name", ValidationStatus.SUCCESSFUL)
    }
}