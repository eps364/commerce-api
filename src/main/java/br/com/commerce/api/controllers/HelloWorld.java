package br.com.commerce.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = { "/" })
public class HelloWorld {

    @GetMapping
    public ResponseEntity<String> hello() {
        log.info(this.getClass().getName() + " | " + "hello | OK");
        return ResponseEntity.ok().body("Hello-World");
    }

}
