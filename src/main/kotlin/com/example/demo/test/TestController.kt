package com.example.demo.test

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController {

    @GetMapping("/test")
    suspend fun test(): ResponseEntity<String> {
        return ResponseEntity.ok("Hello, World!")
    }
}