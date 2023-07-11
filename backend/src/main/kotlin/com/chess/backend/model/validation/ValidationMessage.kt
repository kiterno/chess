package com.chess.backend.model.validation

data class ValidationMessage (
    var message: String,
    var status: ValidationStatus
)