package com.chess.backend.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/v1/chess")
class Authentication {

    @GetMapping
    fun hello(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello From Secure Endpoint");
    }
}