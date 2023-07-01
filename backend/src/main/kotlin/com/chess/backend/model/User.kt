package com.chess.backend.model

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import lombok.Setter
import org.springframework.data.mongodb.core.index.CompoundIndex
import org.springframework.data.mongodb.core.index.CompoundIndexes
import org.springframework.data.mongodb.core.mapping.Document

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "user")
@CompoundIndexes(CompoundIndex(name = "id_username", def = "{'id' : 1, 'username': 1}"))
data class User(val id: Long, val userName: String, val firstName: String, val lastName: String, val email: String, val password: String, val enabled: Boolean, val role: String)
