package com.chess.backend.configs

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@Configuration
@EnableMongoRepositories(basePackages = ["com.chess.backend"])
class MongoConfig: AbstractMongoClientConfiguration() {

    @Value("\${spring.data.mongodb.database}")
    private lateinit var database: String

    override fun getDatabaseName(): String {
        return database
    }

    override fun autoIndexCreation(): Boolean {
        return true
    }
}