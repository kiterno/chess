package com.chess.backend.controller

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/chess/api")
class HelloController {

    // http://localhost:8080/chess/api/hello
    @GetMapping("/hello")
    fun hello(): String {
        return "This is chess backend"
    }
}