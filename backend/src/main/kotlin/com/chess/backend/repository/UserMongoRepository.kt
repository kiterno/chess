package com.chess.backend.repository

import com.chess.backend.model.User
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface UserMongoRepository: MongoRepository<User, Long> {
//    @Query(value = "{ 'status' : ?0 }", fields = "{ 'item' : 1, 'status' : 1, 'size.uom': 1 }")
    @Query("{ 'username' : ?0 }")
    fun findByUserName(username: String): User?

    @Query("{ 'email' : ?0 }")
    fun findByEmail(email: String): User?
}