package com.chess.backend.util.validators

import com.chess.backend.model.User
import com.chess.backend.model.validation.ValidationMessage
import com.chess.backend.model.validation.ValidationStatus
import org.springframework.stereotype.Component
import java.util.regex.Pattern


@Component
class EmailValidatorUtil : Validator() {

    override fun isValid(detail: User, user: User?): ValidationMessage {
        if (detail.email == null) {
            return frameMessage("Email Id is a required field.", ValidationStatus.FAILED)
        }
        else if (detail.email == "") {
            return frameMessage("Email Id is a required field.", ValidationStatus.FAILED)
        } else if (user != null) {
            return frameMessage("Email Id is attached to a different account.", ValidationStatus.FAILED)
        }

        val regexPattern = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";

        return if (patternMatches(detail.email, regexPattern)) {
            frameMessage("Valid Email", ValidationStatus.SUCCESSFUL)
        } else {
            frameMessage("InValid Email Id", ValidationStatus.FAILED)
        }
    }

    fun patternMatches(emailAddress: String, regexPattern: String): Boolean {
        return Pattern.compile(regexPattern)
            .matcher(emailAddress)
            .matches()
    }
}