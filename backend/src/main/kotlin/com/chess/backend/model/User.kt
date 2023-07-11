package com.chess.backend.model

import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document
import java.util.UUID

@Document(collection = "user")
@CompoundIndexes(CompoundIndex(name = "email_username", def = "{'email' : 1, 'username': 1}"))
data class User (
    var id: UUID?,
    @Indexed(name = "username", unique = true)
    val username: String,
    val firstName: String,
    val lastName: String?,
    val email: String?,
    var password: String,
    val userRoles: Role = Role.USER, // getters and setters
    var enabled: Boolean = false
)

